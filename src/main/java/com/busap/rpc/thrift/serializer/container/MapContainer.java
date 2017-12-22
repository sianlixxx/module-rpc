/*
 * 文件名：MapContainer.java
 * 版权：Copyright 2007-2016 成长GPS Tech. Co. Ltd. All Rights Reserved. 
 * 描述： MapContainer.java
 * 修改人：xuanyuan
 * 修改时间：2016年10月17日
 * 修改内容：新增
 */
package com.busap.rpc.thrift.serializer.container;

import java.util.Map;

import com.busap.rpc.thrift.common.FieldType;
import com.busap.rpc.thrift.protobuf.annotation.RemoteSMember;
import com.busap.rpc.thrift.protobuf.annotation.RemoteSModel;

@RemoteSModel
public class MapContainer<K, V> {
    /**
     * map值.
     */
	@RemoteSMember(order=1, fieldType = FieldType.MAP)
    private Map<K, V> value;

    /**
     * 无参构造.
     * 
     */
    public MapContainer() {
        super();
    }

    /**
     * 参数函数.
     * 
     * @param value
     */
    public MapContainer(Map<K, V> value) {
        super();
        this.value = value;
    }

    /**
     * 设置value.
     * 
     * @return 返回value
     */
    public Map<K, V> getValue() {
        return value;
    }

    /**
     * 获取value.
     * 
     * @param value
     *            要设置的value
     */
    public void setValue(Map<K, V> value) {
        this.value = value;
    }
}
