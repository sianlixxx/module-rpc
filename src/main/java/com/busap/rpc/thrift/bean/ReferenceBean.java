package com.busap.rpc.thrift.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.busap.rpc.thrift.config.RefererConfigBean;
import com.busap.rpc.thrift.protobuf.annotation.Reference;

public class ReferenceBean<T> extends RefererConfigBean<T> implements
		FactoryBean, ApplicationContextAware,DisposableBean {
	ApplicationContext applicationContext;
	
	public ReferenceBean() {
		
	}

	public ReferenceBean(Reference reference) {
		
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext=applicationContext;
	}

	@Override
	public T getObject() throws Exception {
		return get();
	}

	@Override
	public Class<?> getObjectType() {
		return this.getInterfaceClass();
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	@Override
	public void destroy() throws Exception {
		
	}


	
}
