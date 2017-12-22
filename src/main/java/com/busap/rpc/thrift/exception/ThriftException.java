package com.busap.rpc.thrift.exception;

public class ThriftException extends RuntimeException{
	/**
	 * 自动生成UID.
	 */
	private static final long serialVersionUID = 1912320291354023742L;

	/**
	 * 
	 * 封装自定义异常消息.
	 * 
	 * @param message
	 *            消息
	 */
	public ThriftException(String message) {
		super(message);
	}

	/**
	 * 
	 * 封装自定义消息和内部异常.
	 * 
	 * @param message
	 *            消息
	 * @param innerException
	 *            内部异常
	 */
	public ThriftException(String message, Exception innerException) {
		super(message, innerException);
	}
}
