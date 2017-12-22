package com.busap.rpc.thrift.bean;

import java.util.Set;

import org.springframework.beans.factory.FactoryBean;

import com.busap.rpc.thrift.pool.ThriftServer;

public class ThriftServerBean implements FactoryBean<ThriftServer>{
	/**
	 * 服务启动端口.
	 */
	private int port;

	/**
	 * 线程池最小工作线程数.
	 */
	private int minWorkThreads;

	/**
	 * 线程池最大工作线程数.
	 */
	private int maxWorkThreads;

	/**
	 * 客户端底层长连接超时断开时间(分钟).
	 */
	private int clientTimeout;

	/**
	 * 客户端请求超时断开时间(秒).
	 */
	private int requestTimeout;

	/**
	 * 使用端口的服务列表.
	 */
	private Set<String> serviceNameSet;
	
	private ThriftServer thriftServer;
	
	

	@Override
	public ThriftServer getObject() throws Exception {
		return this.thriftServer;
	}

	@Override
	public Class<?> getObjectType() {
		return ThriftServer.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getMinWorkThreads() {
		return minWorkThreads;
	}

	public void setMinWorkThreads(int minWorkThreads) {
		this.minWorkThreads = minWorkThreads;
	}

	public int getMaxWorkThreads() {
		return maxWorkThreads;
	}

	public void setMaxWorkThreads(int maxWorkThreads) {
		this.maxWorkThreads = maxWorkThreads;
	}

	public int getClientTimeout() {
		return clientTimeout;
	}

	public void setClientTimeout(int clientTimeout) {
		this.clientTimeout = clientTimeout;
	}

	public int getRequestTimeout() {
		return requestTimeout;
	}

	public void setRequestTimeout(int requestTimeout) {
		this.requestTimeout = requestTimeout;
	}

	public Set<String> getServiceNameSet() {
		return serviceNameSet;
	}

	public void setServiceNameSet(Set<String> serviceNameSet) {
		this.serviceNameSet = serviceNameSet;
	}

	public ThriftServer getThriftServer() {
		return thriftServer;
	}

	public void setThriftServer(ThriftServer thriftServer) {
		this.thriftServer = thriftServer;
	}



}
