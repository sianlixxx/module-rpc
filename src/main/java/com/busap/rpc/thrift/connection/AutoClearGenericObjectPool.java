package com.busap.rpc.thrift.connection;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.thrift.transport.TTransport;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class AutoClearGenericObjectPool<T> extends
		GenericObjectPool<TTransport> implements ApplicationContextAware {
	private ApplicationContext applicationContext;

	public AutoClearGenericObjectPool(PooledObjectFactory<TTransport> factory,
			GenericObjectPoolConfig config) {
		super(factory, config);
	}


	@Override
	public void returnObject(TTransport obj) {
		super.returnObject((TTransport) obj);
		if (getNumIdle() >= getNumActive()) {
			clear();
		}
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

}