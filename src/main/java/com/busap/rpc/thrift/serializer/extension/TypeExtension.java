/*
 * 文件名：TypeExtension.java
 * 版权：Copyright 2007-2016 成长GPS Tech. Co. Ltd. All Rights Reserved. 
 * 描述： TypeExtension.java
 * 修改人：xuanyuan
 * 修改时间：2016年10月15日
 * 修改内容：新增
 */
package com.busap.rpc.thrift.serializer.extension;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.thrift.protocol.TType;

import com.busap.rpc.thrift.exception.ThriftException;
import com.busap.rpc.thrift.idl.BadRequestException;
import com.busap.rpc.thrift.serializer.container.BasicContainer;
import com.busap.rpc.thrift.serializer.container.ListContainer;
import com.busap.rpc.thrift.serializer.container.MapContainer;
import com.busap.rpc.thrift.serializer.container.SetContainer;

public final class TypeExtension {
    /**
     * 
     * 根据Class类型判断是否基本容器类型.
     * 
     * @param clazz
     *            字节码实例
     * @return 结果
     */
    public static boolean isBasic(Class<?> clazz) {
        if (clazz.isPrimitive() || isWrapClass(clazz)) {
            return true;
        } else if (clazz == String.class || clazz == BigDecimal.class || clazz == Date.class || clazz == byte[].class || clazz == UUID.class) {
            return true;
        }

        return false;
    }

    /**
     * 
     * 根据Class类型判断是否List.
     * 
     * @param clazz
     *            字节码实例
     * @return 结果
     */
    public static boolean isList(Class<?> clazz) {
        return List.class.isAssignableFrom(clazz);
    }

    /**
     * 
     * 根据Class类型判断是否Map.
     * 
     * @param clazz
     *            字节码实例
     * @return 结果
     */
    public static boolean isMap(Class<?> clazz) {
        return Map.class.isAssignableFrom(clazz);
    }

    /**
     * 
     * 根据Class类型判断是否Set.
     * 
     * @param clazz
     *            字节码实例
     * @return 结果
     */
    public static boolean isSet(Class<?> clazz) {
        return Set.class.isAssignableFrom(clazz);
    }

    /**
     * 判断是否Java体系自带类(区分用户类).
     * 
     * @param clazz
     *            字节码实例
     * @return true java类 / false 用户类
     */
    public static boolean isJavaClass(Class<?> clazz) {
        return clazz != null && clazz.getClassLoader() == null;
    }

    /**
     * 判断Class是否基本类型的封装类型.
     * 
     * @param clazz
     *            字节码对象
     * @return true 是
     */
    public static boolean isWrapClass(Class<?> clazz) {
        try {
            // 封装类型都有静态常量字段TYPE
            Field field = clazz.getField("TYPE");
            if (field != null) {
                // 如果不是静态字段，说明是用户自定义的TYPE字段
                Object obj = field.get(null);
                if (obj != null) {
                    return ((Class<?>) obj).isPrimitive();
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断是否支持的序列化类型.
     * 
     * @param clazz
     *            字节码对象
     */
    public static void unSupported(Class<?> clazz) {
        if (clazz.isArray() || clazz.isEnum()) {
            throw new ThriftException(String.format("当前不支持%s序列化", clazz.getName()));
        }
    }

    /**
     * 是否thrift容器类型.
     * 
     * @param clazz
     *            带判断类型
     * @return true 是
     */
    public static boolean isContainerClass(Class<?> clazz) {
        return clazz == BasicContainer.class || clazz == ListContainer.class || clazz == MapContainer.class || clazz == SetContainer.class;
    }

    /**
     * TODO 添加方法注释.
     * 
     * @param clazz
     * @return
     * @throws BadRequestException
     */
    public static byte toThriftType(Class<?> clazz) throws BadRequestException {
        // 一般来说，用户自定义的model和List要多，所以先判断
        if (!isJavaClass(clazz)) {
            return TType.STRUCT;
        } else if (isList(clazz)) {
            return TType.LIST;
        } else if (clazz == Long.class || clazz == Long.TYPE || clazz == Date.class) {
            return TType.I64;
        } else if (clazz == Integer.class || clazz == Integer.TYPE) {
            return TType.I32;
        } else if (clazz == String.class || clazz == UUID.class || clazz == byte[].class) {
            return TType.STRING;
        } else if (clazz == BigDecimal.class || clazz == Double.class || clazz == Float.class || clazz == Double.TYPE || clazz == Float.TYPE) {
            return TType.DOUBLE;
        } else if (isMap(clazz)) {
            return TType.MAP;
        } else if (clazz == Boolean.class || clazz == Boolean.TYPE) {
            return TType.BOOL;
        } else if (clazz == Byte.class || clazz == Byte.TYPE) {
            return TType.BYTE;
        } else if (clazz == Short.class || clazz == Short.TYPE) {
            return TType.I16;
        } else if (isSet(clazz)) {
            return TType.SET;
        }

        unSupported(clazz);

        // 如果有漏掉的类型，先默认
        return TType.STRUCT;
    }
}
