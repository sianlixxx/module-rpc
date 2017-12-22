/*
 * 文件名：ValueContainer.java
 * 版权：Copyright 2007-2016 成长GPS Tech. Co. Ltd. All Rights Reserved. 
 * 描述： ValueContainer.java
 * 修改人：xuanyuan
 * 修改时间：2016年10月15日
 * 修改内容：新增
 */
package com.busap.rpc.thrift.serializer.container;

import com.busap.rpc.thrift.common.FieldType;
import com.busap.rpc.thrift.protobuf.annotation.RemoteSMember;
import com.busap.rpc.thrift.protobuf.annotation.RemoteSModel;

@RemoteSModel
public class BasicContainer<T> {
    /**
     * 容器值.
     */
	@RemoteSMember(order=1, fieldType = FieldType.STRUCT)
    private T value;

    /**
     * 无参构造.
     * 
     */
    public BasicContainer() {
        super();
    }

    /**
     * 参数函数.
     * 
     * @param value
     */
    public BasicContainer(T value) {
        super();
        this.value = value;
    }

    /**
     * 获取value.
     * 
     * @return 返回value
     */
    public T getValue() {
        return value;
    }

    /**
     * 设置value.
     * 
     * @param value
     *            要设置的value
     */
    public void setValue(T value) {
        this.value = value;
    }
}
