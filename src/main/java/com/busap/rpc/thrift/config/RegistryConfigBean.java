package com.busap.rpc.thrift.config;

public class RegistryConfigBean extends AbstractInterfaceConfig {
	
	
	public RegistryConfigBean() {
		super();
	}
	
	
	
	private String address;
	private int sessionTimeoutMs = 3000;
	private int connectionTimeoutMs = 5000;
	private int maxCloseWaitMs = 5000;
	private int port;
	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getSessionTimeoutMs() {
		return sessionTimeoutMs;
	}
	public void setSessionTimeoutMs(int sessionTimeoutMs) {
		this.sessionTimeoutMs = sessionTimeoutMs;
	}
	public int getConnectionTimeoutMs() {
		return connectionTimeoutMs;
	}
	public void setConnectionTimeoutMs(int connectionTimeoutMs) {
		this.connectionTimeoutMs = connectionTimeoutMs;
	}
	public int getMaxCloseWaitMs() {
		return maxCloseWaitMs;
	}
	public void setMaxCloseWaitMs(int maxCloseWaitMs) {
		this.maxCloseWaitMs = maxCloseWaitMs;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
}
