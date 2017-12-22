/*
 * 文件名：ListContainer.java
 * 版权：Copyright 2007-2016 成长GPS Tech. Co. Ltd. All Rights Reserved. 
 * 描述： ListContainer.java
 * 修改人：xuanyuan
 * 修改时间：2016年10月17日
 * 修改内容：新增
 */
package com.busap.rpc.thrift.serializer.container;

import java.util.List;

import com.busap.rpc.thrift.common.FieldType;
import com.busap.rpc.thrift.protobuf.annotation.RemoteSMember;
import com.busap.rpc.thrift.protobuf.annotation.RemoteSModel;


@RemoteSModel
public class ListContainer<T> {

    /**
     * list数据.
     */
	@RemoteSMember(order=1, fieldType = FieldType.LIST)
    private List<T> value;

    /**
     * 无参构造.
     * 
     */
    public ListContainer() {
        super();
    }

    /**
     * 参数函数.
     * 
     * @param value
     */
    public ListContainer(List<T> value) {
        super();
        this.value = value;
    }

    /**
     * 设置value.
     * 
     * @return 返回value
     */
    public List<T> getValue() {
        return value;
    }

    /**
     * 获取value.
     * 
     * @param value
     *            要设置的value
     */
    public void setValue(List<T> value) {
        this.value = value;
    }
}
