package com.busap.rpc.thrift.pool;

public abstract class AbstractConfig {
	/**
	 * 应用系统ID
	 */
	private int pid;
	/**
	 * 服务启动端口.
	 */
	private int port;
	/**
	 * socketTimeout
	 */
	private int socketTimeout = 3000;


	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getSocketTimeout() {
		return socketTimeout;
	}

	public void setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}
}
