package com.busap.rpc.thrift.config;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import com.busap.rpc.thrift.pool.ConnectionProvider;
import com.busap.rpc.thrift.protobuf.annotation.Reference;
import com.busap.rpc.thrift.protobuf.handler.RefererInvocationHandler;

public class RefererConfigBean<T> extends AbstractInterfaceConfig {
	protected List<ConnectionProvider> clients = new ArrayList<ConnectionProvider>();

	private Reference reference;
	private Class<?> interfaceClass;
	private String interfaceName;
	private transient volatile T ref;
	public RefererConfigBean() {
		
	}
	public RefererConfigBean(Reference reference) {
		this.reference = reference;
	}
	
	public T getRef() {
		return ref;
	}

	public void setRef(T ref) {
		this.ref = ref;
	}

	public Class<?> getInterfaceClass() {
		return interfaceClass;
	}

	public void setInterfaceClass(Class<?> interfaceClass) {
		this.interfaceClass = interfaceClass;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public List<ConnectionProvider> getClients() {
		return clients;
	}

	public void setClients(List<ConnectionProvider> clients) {
		this.clients = clients;
	}
	public Reference getReference() {
		return reference;
	}

	public void setReference(Reference reference) {
		this.reference = reference;
	}

    public synchronized T get() {
        if (ref == null) {
            init();
        }
        return ref;
    }
	private void init() {
		ref = createProxy();
	}
	private T createProxy() {
		
		System.out.println("");
        return (T) Proxy.newProxyInstance(RefererConfigBean.class.getClassLoader(), new Class<?>[]{interfaceClass}, new RefererInvocationHandler(interfaceClass,interfaceName, clients));
	}


}
