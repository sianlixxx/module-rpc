/*
 * 文件名：ThriftSerializer.java
 * 版权：Copyright 2007-2016 成长GPS Tech. Co. Ltd. All Rights Reserved. 
 * 描述： ThriftSerializer.java
 * 修改人：xuanyuan
 * 修改时间：2016年8月27日
 * 修改内容：新增
 */
package com.busap.rpc.thrift.serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TIOStreamTransport;

import com.busap.rpc.thrift.exception.ThriftException;
import com.busap.rpc.thrift.serializer.container.BasicContainer;
import com.busap.rpc.thrift.serializer.container.ListContainer;
import com.busap.rpc.thrift.serializer.container.MapContainer;
import com.busap.rpc.thrift.serializer.container.SetContainer;
import com.busap.rpc.thrift.serializer.extension.TProtocolExtension;
import com.busap.rpc.thrift.serializer.extension.TypeExtension;

public class ThriftSerializer {
    /**
     * Object转为byte[].
     * 
     * @param instance
     *            任意对象实例
     * @return 字节数组
     */
    public static byte[] serialize(Object instance) {
        TIOStreamTransport transport = null;
        try {
            Class<?> clazz = instance.getClass();
            TypeExtension.unSupported(clazz);
            // 对于thrift来说只有list、map、set是容器类型，其他的都可以作为基本类型处理（数组只有byte[]）
            if (TypeExtension.isBasic(clazz)) {
                instance = BasicContainer.class.getConstructor(Object.class).newInstance(instance);
            } else if (TypeExtension.isList(clazz)) {
                instance = ListContainer.class.getConstructor(List.class).newInstance(instance);
            } else if (TypeExtension.isMap(clazz)) {
                instance = MapContainer.class.getConstructor(Map.class).newInstance(instance);
            } else if (TypeExtension.isSet(clazz)) {
                instance = SetContainer.class.getConstructor(Set.class).newInstance(instance);
            }

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            transport = new TIOStreamTransport(null, stream);
            TBinaryProtocol protocol = new TBinaryProtocol(transport);
            TProtocolExtension.writeStruct(protocol, instance);
            protocol.getTransport().flush();
            return stream.toByteArray();
        } catch (Exception ex) {
            throw new ThriftException("thrift序列化异常", ex);
        } finally {
            if (transport != null)
                transport.close();
        }
    }

    /**
     * 
     * byte[]转为Object.
     * 
     * @param buffer
     *            字节
     * @param paramType
     *            参数类型
     * @return 对象实例
     */
    public static Object deserialize(byte[] buffer, Type paramType) {
        boolean isContainer = false;
        Class<?> clazz = null;
        if (paramType instanceof ParameterizedType) {
            clazz = (Class<?>) ((ParameterizedType) paramType).getRawType();
        } else {
            clazz = (Class<?>) paramType;
        }

        TypeExtension.unSupported(clazz);
        if (TypeExtension.isBasic(clazz)) {
            clazz = BasicContainer.class;
            isContainer = true;
        } else if (TypeExtension.isList(clazz)) {
            clazz = ListContainer.class;
            isContainer = true;
        } else if (TypeExtension.isMap(clazz)) {
            clazz = MapContainer.class;
            isContainer = true;
        } else if (TypeExtension.isSet(clazz)) {
            clazz = SetContainer.class;
            isContainer = true;
        }

        TIOStreamTransport transport = null;
        Object instance = null;
        try {
            ByteArrayInputStream stream = new ByteArrayInputStream(buffer);
            transport = new TIOStreamTransport(stream, null);
            TBinaryProtocol protocol = new TBinaryProtocol(transport);
            instance = TProtocolExtension.readStruct(protocol, clazz, paramType);
        } catch (Exception ex) {
            throw new ThriftException("thrift反序列化异常", ex);
        } finally {
            if (transport != null)
                transport.close();
        }

        // 容器类型要拆解
        if (isContainer) {
            try {
                Field field = clazz.getDeclaredField("value");
                field.setAccessible(true);
                instance = field.get(instance);
            } catch (Exception ex) {
                // 这个就不处理了，不会失败
            }
        }

        return instance;
    }
}
