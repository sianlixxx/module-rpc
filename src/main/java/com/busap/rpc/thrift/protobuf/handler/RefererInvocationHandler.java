package com.busap.rpc.thrift.protobuf.handler;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.transport.TTransport;

import com.busap.rpc.thrift.common.ThriftSRequestWrapper;
import com.busap.rpc.thrift.idl.ThriftSHandler;
import com.busap.rpc.thrift.idl.ThriftSParameter;
import com.busap.rpc.thrift.idl.ThriftSRequest;
import com.busap.rpc.thrift.idl.ThriftSResponse;
import com.busap.rpc.thrift.pool.ConnectionProvider;
import com.busap.rpc.thrift.utils.Utils;



public class RefererInvocationHandler<T> implements InvocationHandler {

    private AtomicLong atomicLong = new AtomicLong(0);

    private Class<T> clz;
    private List<ConnectionProvider> clients ;
    private  String serviceName;

    public RefererInvocationHandler(Class<T> clz, String serviceName, List<ConnectionProvider> clients) {
        this.clz = clz;
        this.clients = clients;
        this.serviceName=serviceName;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        ThriftSRequest sRequest = new ThriftSRequest();
        sRequest.setServiceName(this.serviceName);
        sRequest.setMethodName(method.getName());
        sRequest.setHeaders(new HashMap<String, String>());
        sRequest.setParameters(new ArrayList<ThriftSParameter>());
        ThriftSRequestWrapper thriftSRequestWrapper = new ThriftSRequestWrapper(sRequest);
 //       thriftSRequestWrapper.setUri();
        thriftSRequestWrapper.setVersion(Utils.getVersion());
        thriftSRequestWrapper.setClientPid(Utils.getPid().toString());
        thriftSRequestWrapper.setClientHostName(Utils.getHostName());
        thriftSRequestWrapper.setClientRuntime("Java ");//Environment.Version.ToString();

    	ConnectionProvider connectionProvider = clients.get(0);
        TTransport  connection = connectionProvider.getConnection();
        TMultiplexedProtocol protocol = new TMultiplexedProtocol(new TBinaryProtocol(connection), "ThriftSHandler");
        ThriftSHandler.Client client = new ThriftSHandler.Client(protocol);
        ThriftSResponse   sResponse = client.Process(sRequest);
		return sResponse.getResult();
    }

}
