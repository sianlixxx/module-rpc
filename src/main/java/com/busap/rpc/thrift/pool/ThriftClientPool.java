package com.busap.rpc.thrift.pool;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

public class ThriftClientPool extends AbstractConfig implements FactoryBean{
	// extends GenericObjectPoolConfig
	// 默认协议
	private Class<?> DEFAULT_PROTOCOL_CLASS = TBinaryProtocol.class;
	private String host;
	private String protocol;
	private int maxIdle = 10;
	private int maxTotal = 10;
	private int socketTimeout = 3000;
	private long maxWaitMillis;
	private long minEvictableIdleTimeMillis;
	private boolean testOnReturn = false;
	private boolean testOnBorrow = false;
	private boolean testWhileIdle = false;
	private long timeBetweenEvictionRunsMillis = 3000;

	public Class<?> getDEFAULT_PROTOCOL_CLASS() {
		return DEFAULT_PROTOCOL_CLASS;
	}

	public void setDEFAULT_PROTOCOL_CLASS(Class<?> dEFAULT_PROTOCOL_CLASS) {
		DEFAULT_PROTOCOL_CLASS = dEFAULT_PROTOCOL_CLASS;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}

	public int getMaxTotal() {
		return maxTotal;
	}

	public void setMaxTotal(int maxTotal) {
		this.maxTotal = maxTotal;
	}

	public long getMaxWaitMillis() {
		return maxWaitMillis;
	}

	public void setMaxWaitMillis(long maxWaitMillis) {
		this.maxWaitMillis = maxWaitMillis;
	}

	public long getMinEvictableIdleTimeMillis() {
		return minEvictableIdleTimeMillis;
	}

	public void setMinEvictableIdleTimeMillis(long minEvictableIdleTimeMillis) {
		this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
	}

	public boolean isTestOnReturn() {
		return testOnReturn;
	}

	public void setTestOnReturn(boolean testOnReturn) {
		this.testOnReturn = testOnReturn;
	}

	public boolean isTestOnBorrow() {
		return testOnBorrow;
	}

	public void setTestOnBorrow(boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}

	public boolean isTestWhileIdle() {
		return testWhileIdle;
	}

	public void setTestWhileIdle(boolean testWhileIdle) {
		this.testWhileIdle = testWhileIdle;
	}

	public long getTimeBetweenEvictionRunsMillis() {
		return timeBetweenEvictionRunsMillis;
	}

	public void setTimeBetweenEvictionRunsMillis(
			long timeBetweenEvictionRunsMillis) {
		this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public int getSocketTimeout() {
		return socketTimeout;
	}

	public void setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	@Override
	public Object getObject() throws Exception {
		return this;
	}
	@Override
	public Class getObjectType() {
		return GenericObjectPoolConfig.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
