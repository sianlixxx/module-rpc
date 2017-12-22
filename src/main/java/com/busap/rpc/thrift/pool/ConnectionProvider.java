package com.busap.rpc.thrift.pool;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.thrift.transport.TTransport;
import org.springframework.beans.factory.InitializingBean;

import com.busap.rpc.thrift.connection.ConnectionPoolFactory;

public class ConnectionProvider extends ThriftClientPool implements
		IConnection, InitializingBean {

	GenericObjectPool<TTransport> poolFactory = null;

	private boolean autoStartup;

	private ConnectionProvider() {
		super();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		GenericObjectPoolConfig	config = new GenericObjectPoolConfig();
		ConnectionPoolFactory factory = new ConnectionPoolFactory(this);
		poolFactory = new GenericObjectPool<TTransport>(factory,config);
	}

	@Override
	public TTransport getConnection() throws Exception {
		return poolFactory.borrowObject();
	}

	@Override
	public void returnConnection(TTransport tTransport) {
		poolFactory.returnObject(tTransport);

	}

	public boolean isAutoStartup() {
		return autoStartup;
	}

	public void setAutoStartup(boolean autoStartup) {
		this.autoStartup = autoStartup;
	}

}
