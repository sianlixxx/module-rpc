package com.busap.rpc.thrift.pool;

public class ThriftServer extends AbstractConfig implements Runnable{
	
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

	private ThriftServer(int port, int minWorkThreads, int maxWorkThreads,
			int clientTimeout, int requestTimeout,int socketTimeout) {
		this.minWorkThreads = minWorkThreads;
		this.maxWorkThreads = maxWorkThreads;
		this.clientTimeout = clientTimeout;
		this.requestTimeout = requestTimeout;
	}

	@Override
	public void run() {
		System.out.print("thrift sever 启动.....");
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

}
