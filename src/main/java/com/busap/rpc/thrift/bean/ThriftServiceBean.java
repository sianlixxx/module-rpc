package com.busap.rpc.thrift.bean;

import java.lang.reflect.Method;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.support.AbstractApplicationContext;

import com.busap.rpc.thrift.protobuf.annotation.RemoteService;

/**
 * 
 * @Title: TaskHandlerBean.java
 * @Package com.alice.jecc.beans
 * @author jecc
 * @date 2017-9-13 下午3:53:15
 * @description:
 * @version v1.0
 */
public class ThriftServiceBean<T> implements InitializingBean, DisposableBean, 
		ApplicationContextAware, BeanNameAware {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4362584897454700743L;

	// 接口类型
	private String interfaceName;

	private Class<?> interfaceClass;

	private RemoteService remoteService;

	private ApplicationContext applicationContext;
	
    private transient boolean supportedApplicationListener;

	private transient String beanName;
	// 接口实现类引用
	private T ref;

	// 服务名称
	private String path;

	public ThriftServiceBean(RemoteService remoteService) {
		this.remoteService = remoteService;
	}

	public T getRef() {
		return ref;
	}

	public void setRef(T ref) {
		this.ref = ref;
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		if (applicationContext != null) {
			this.applicationContext = applicationContext;
		    try {
	            Method method = applicationContext.getClass().getMethod("addApplicationListener", new Class<?>[]{ApplicationListener.class}); // 兼容Spring2.0.1
	            method.invoke(applicationContext, new Object[] {this});
	            supportedApplicationListener = true;
	        } catch (Throwable t) {
                if (applicationContext instanceof AbstractApplicationContext) {
    	            try {
    	                Method method = AbstractApplicationContext.class.getDeclaredMethod("addListener", new Class<?>[]{ApplicationListener.class}); // 兼容Spring2.0.1
                        if (! method.isAccessible()) {
                            method.setAccessible(true);
                        }
    	                method.invoke(applicationContext, new Object[] {this});
                        supportedApplicationListener = true;
    	            } catch (Throwable t2) {
    	            }
	            }
	        }
		}
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void afterPropertiesSet() throws Exception {
		// this.applicationContext.publishEvent());
	}

	@Override
	public void setBeanName(String name) {
		this.beanName = name;
	}

	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub

	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public Class<?> getInterfaceClass() {
		return interfaceClass;
	}

	public void setInterfaceClass(Class<?> interfaceClass) {
		this.interfaceClass = interfaceClass;
	}

	public RemoteService getRemoteService() {
		return remoteService;
	}

	public void setRemoteService(RemoteService remoteService) {
		this.remoteService = remoteService;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setInterface(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public void setInterface(Class<?> class1) {
		if (interfaceClass != null && !interfaceClass.isInterface()) {
			throw new IllegalStateException("The interface class "
					+ interfaceClass + " is not a interface!");
		}
		this.interfaceClass = interfaceClass;
		setInterface(interfaceClass == null ? (String) null : interfaceClass
				.getName());
	}

}
