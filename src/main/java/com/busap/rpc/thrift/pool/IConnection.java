package com.busap.rpc.thrift.pool;

import org.apache.thrift.transport.TTransport;

public interface IConnection {
	/**
     * 从连接池里获取一个TProtocol对象
     * @return
	 * @throws Exception 
     */
	TTransport getConnection() throws Exception;

    /**
     * 将一个TProtocol对象放回连接池
     * @param tProtocol
     */
    void returnConnection(TTransport tTransport);

}
