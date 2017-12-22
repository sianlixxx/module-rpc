package com.busap.rpc.thrift.pool;

import org.apache.thrift.transport.TTransport;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ThriftTemplate implements InitializingBean,
		ApplicationContextAware {

	private IConnection iConnection;
	private ApplicationContext applicationContext;

	@Override
	public void afterPropertiesSet() throws Exception {
		ConnectionProvider connectionProvider=applicationContext.getBean(ConnectionProvider.class);
		TTransport	tTransport=connectionProvider.getConnection();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
}
