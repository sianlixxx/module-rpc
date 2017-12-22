package com.busap.rpc.thrift.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class ServerConfigBean  extends ApplicationConfig implements ApplicationListener<ContextRefreshedEvent>, ApplicationContextAware, DisposableBean {
    private String id;
    private Integer port;
    private ApplicationContext applicationContext;
    

	@Override
	public void destroy() throws Exception {
		
	}
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		
	}
}
