/*
 * 文件名：SetContainer.java
 * 版权：Copyright 2007-2016 成长GPS Tech. Co. Ltd. All Rights Reserved. 
 * 描述： SetContainer.java
 * 修改人：xuanyuan
 * 修改时间：2016年10月17日
 * 修改内容：新增
 */
package com.busap.rpc.thrift.serializer.container;

import java.util.Set;

import com.busap.rpc.thrift.common.FieldType;
import com.busap.rpc.thrift.protobuf.annotation.RemoteSMember;
import com.busap.rpc.thrift.protobuf.annotation.RemoteSModel;

@RemoteSModel
public class SetContainer<T> {

    /**
     * set值.
     */
	@RemoteSMember(order=1, fieldType = FieldType.SET)
    private Set<T> value;

    /**
     * 无参构造.
     * 
     */
    public SetContainer() {
        super();
    }

    /**
     * 参数函数.
     * 
     * @param value
     */
    public SetContainer(Set<T> value) {
        super();
        this.value = value;
    }

    /**
     * 设置value.
     * 
     * @return 返回value
     */
    public Set<T> getValue() {
        return value;
    }

    /**
     * 获取value.
     * 
     * @param value
     *            要设置的value
     */
    public void setValue(Set<T> value) {
        this.value = value;
    }
}
