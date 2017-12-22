/*
 * 文件名：ProtocolExtension.java
 * 版权：Copyright 2007-2016 成长GPS Tech. Co. Ltd. All Rights Reserved. 
 * 描述： ProtocolExtension.java
 * 修改人：xuanyuan
 * 修改时间：2016年10月15日
 * 修改内容：新增
 */
package com.busap.rpc.thrift.serializer.extension;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TList;
import org.apache.thrift.protocol.TMap;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TSet;
import org.apache.thrift.protocol.TStruct;
import org.apache.thrift.protocol.TType;

import com.busap.rpc.thrift.protobuf.annotation.RemoteSMember;

public final class TProtocolExtension {

    /**
     * 将java对象(Object)转换为thrift对象(TStruct)来序列化
     * 
     * @param protocol
     *            当前序列化协议
     * @param instance
     *            序列化对象实例
     * @return 返回转换的thrift对象TStruct
     * @throws Exception
     *             异常信息
     */
    public static TStruct writeStruct(TProtocol protocol, Object instance) throws Exception {
        TStruct struct = new TStruct();
        protocol.writeStructBegin(struct);

        // 反射获取实例的所有字段
        Class<?> clazz = instance.getClass();
        Field[] fields = clazz.getDeclaredFields();
        TField tfield = null;
        RemoteSMember memberAnnotation = null;
        Object obj = null;
        Class<?> cls = null;
        for (Field field : fields) {
            memberAnnotation = field.getAnnotation(RemoteSMember.class);
            if (memberAnnotation != null) {
                field.setAccessible(true);
                obj = field.get(instance);
                // 没有值就不用写了
                if (obj != null) {
                    cls = obj.getClass();
                    tfield = new TField(field.getName(), TypeExtension.toThriftType(cls),memberAnnotation.order());
                    protocol.writeFieldBegin(tfield);
                    if (TypeExtension.isContainerClass(clazz)) {
                        writeValue(protocol, tfield.type, obj, cls);
                    } else {
                        writeValue(protocol, tfield.type, obj, field.getGenericType());
                    }
                    // writeValue(protocol, tfield.type, obj, cls, field.getGenericType());
                    protocol.writeFieldEnd();
                }
            }
        }
        protocol.writeFieldStop();
        protocol.writeStructEnd();

        return struct;
    }

    /**
     * 从thrift序列化协议中读取指定类型的数据.
     * 
     * @param protocol
     *            thrift序列化协议
     * @param clazz
     *            读取的类型
     * @param typeParams
     *            clazz参数对应的泛型参数
     * @return 读取结果
     * @throws Exception
     */
    public static Object readStruct(TProtocol protocol, Class<?> clazz, Type typeParam) throws Exception {
    	RemoteSMember memberAnnotation = null;
        Field[] fields = clazz.getDeclaredFields();
        Map<Short, Field> filedMap = new HashMap<Short, Field>();
        for (Field field : fields) {
            memberAnnotation = field.getAnnotation(RemoteSMember.class);
            if (memberAnnotation != null) {
                filedMap.put(memberAnnotation.order(), field);
            }
        }

        Object fieldVal = null;
        Object instance = clazz.getConstructor().newInstance();
        while (true) {
            TField tfield = protocol.readFieldBegin();
            if (tfield.type == TType.STOP) {
                break;
            }

            // 当前读取出的字段
            Field field = filedMap.get(Integer.valueOf(tfield.id));
            field.setAccessible(true);
            if (TypeExtension.isContainerClass(clazz)) {
                fieldVal = readValue(protocol, tfield.type, typeParam);
            } else {
                fieldVal = readValue(protocol, tfield.type, field.getGenericType());
            }

            field.set(instance, fieldVal);
        }

        return instance;
    }

    /**
     * 
     * 在当前协议写入序列化的值.
     * 
     * @param protocol
     *            协议
     * @param thriftType
     *            对应的thrift类型
     * @param value
     *            值
     * @param typeParams
     *            写入值对应的具体类型
     * @throws Exception
     *             异常信息
     */
    private static void writeValue(TProtocol protocol, byte thriftType, Object value, Type typeParam) throws Exception {
        switch (thriftType) {
            case TType.I32:
                protocol.writeI32((int) value);
                break;
            case TType.STRING: {
                // thrift中byte[]对应binary，是一种特殊的字符串
                Class<?> clazz = (Class<?>) typeParam;
                if (clazz == String.class) {
                    protocol.writeString((String) value);
                } else if (clazz == BigDecimal.class) {
                    protocol.writeString(value.toString());
                } else if (clazz == byte[].class) {
                    protocol.writeBinary(ByteBuffer.wrap((byte[]) value));
                } else if (clazz == UUID.class) {
                    protocol.writeString(((UUID) value).toString());
                }
                break;
            }
            case TType.STRUCT:
                writeStruct(protocol, value);
                break;
            case TType.LIST: {
                List<?> list = (List<?>) value;
                if (!list.isEmpty()) {
                    Class<?> elemSrcType = list.get(0).getClass();
                    byte elemType = TypeExtension.toThriftType(elemSrcType);
                    TList tlist = new TList(elemType, list.size());
                    protocol.writeListBegin(tlist);
                    for (Object obj : list) {
                        writeValue(protocol, elemType, obj, elemSrcType);
                    }
                    protocol.writeListEnd();
                }
                return;
            }
            case TType.BOOL:
                protocol.writeBool((boolean) value);
                break;
            case TType.BYTE:
                protocol.writeByte((byte) value);
                break;
            case TType.DOUBLE: {
                Class<?> clazz = (Class<?>) typeParam;
                if (clazz == BigDecimal.class) {
                    protocol.writeDouble(((BigDecimal) value).doubleValue());
                } else {
                    protocol.writeDouble((double) value);
                }
                break;
            }
            case TType.I16:
                protocol.writeI16((short) value);
                break;
            case TType.I64: {
                Class<?> clazz = (Class<?>) typeParam;
                if (clazz == Date.class) {
                    protocol.writeI64(((Date) value).getTime());
                } else {
                    protocol.writeI64((long) value);
                }
                break;
            }
            case TType.MAP: {
                Map<?, ?> map = (Map<?, ?>) value;
                if (!map.isEmpty()) {
                    Map.Entry<?, ?> firstEntry = (Map.Entry<?, ?>) map.entrySet().toArray()[0];
                    Class<?> keyType = firstEntry.getKey().getClass();
                    Class<?> valueType = firstEntry.getValue().getClass();
                    byte keyElemType = TypeExtension.toThriftType(keyType);
                    byte valueElemType = TypeExtension.toThriftType(valueType);

                    TMap tmap = new TMap(keyElemType, valueElemType, map.size());
                    protocol.writeMapBegin(tmap);

                    for (Map.Entry<?, ?> entry : map.entrySet()) {
                        // 写入key
                        writeValue(protocol, keyElemType, entry.getKey(), keyType);
                        // 写入value
                        writeValue(protocol, valueElemType, entry.getValue(), valueType);
                    }
                    protocol.writeMapEnd();
                }
                return;
            }
            case TType.SET: {
                Set<?> set = (Set<?>) value;
                if (!set.isEmpty()) {
                    Class<?> elemSrcType = set.toArray()[0].getClass();
                    byte elemType = TypeExtension.toThriftType(elemSrcType);
                    TSet tset = new TSet(elemType, set.size());
                    protocol.writeSetBegin(tset);
                    for (Object obj : set) {
                        writeValue(protocol, elemType, obj, elemSrcType);
                    }
                    protocol.writeSetEnd();
                }
                return;
            }
        }
    }

    /**
     * 
     * 从协议中读取值.
     * 
     * @param protocol
     *            协议
     * @param thriftType
     *            对应的thrift类型
     * @param typeParams
     *            当前读取值对应的具体类型
     * @return 返回读取值
     * @throws Exception
     *             异常信息
     */
    private static Object readValue(TProtocol protocol, byte thriftType, Type typeParam) throws Exception {
        switch (thriftType) {
            case TType.I32:
                return protocol.readI32();
            case TType.STRING: {
                // thrift中byte[]对应binary，是一种特殊的字符串
                Class<?> clazz = (Class<?>) typeParam;
                if (clazz == String.class) {
                    return protocol.readString();
                } else if (clazz == BigDecimal.class) {
                    return new BigDecimal(protocol.readString());
                } else if (clazz == byte[].class) {
                    return protocol.readBinary();
                } else if (clazz == UUID.class) {
                    return UUID.fromString(protocol.readString());
                }
            }
            case TType.STRUCT:
                return readStruct(protocol, (Class<?>) typeParam, null);
            case TType.LIST: {
                // list的元素类型
                ParameterizedType srcType = (ParameterizedType) typeParam;
                Type elemSrcType = srcType.getActualTypeArguments()[0];
                // 创建list
                Class<?> clazz = (Class<?>) srcType.getRawType();
                if (clazz == List.class) {
                    clazz = ArrayList.class;
                }
                Object list = clazz.newInstance();
                // 读取list
                Object elemObj = null;
                Method method = clazz.getMethod("add", Object.class);
                TList tlist = protocol.readListBegin();
                for (int i = 0; i < tlist.size; i++) {
                    elemObj = readValue(protocol, tlist.elemType, (Class<?>) elemSrcType);
                    method.invoke(list, elemObj);
                }
                protocol.readListEnd();

                return list;
            }
            case TType.BOOL:
                return protocol.readBool();
            case TType.BYTE:
                return protocol.readByte();
            case TType.DOUBLE: {
                Class<?> clazz = (Class<?>) typeParam;
                if (clazz == BigDecimal.class) {
                    return new BigDecimal(protocol.readDouble());
                }
                return protocol.readDouble();
            }
            case TType.I16:
                return protocol.readI16();
            case TType.I64: {
                Class<?> clazz = (Class<?>) typeParam;
                if (clazz == Date.class) {
                    return new Date(protocol.readI64());
                }
                return protocol.readI64();
            }
            case TType.MAP: {
                // map的key-value类型
                ParameterizedType type = (ParameterizedType) typeParam;
                Type keySrcType = type.getActualTypeArguments()[0];
                Type valueSrcType = type.getActualTypeArguments()[1];
                // 创建map
                Class<?> clazz = (Class<?>) type.getRawType();
                if (clazz == Map.class) {
                    clazz = HashMap.class;
                }
                Object map = clazz.getConstructor().newInstance();
                // 读取list
                Object keyObj = null;
                Object valueObj = null;
                Method method = clazz.getMethod("put", Object.class, Object.class);
                TMap tmap = protocol.readMapBegin();
                for (int i = 0; i < tmap.size; i++) {
                    // key读取
                    keyObj = readValue(protocol, tmap.keyType, keySrcType);
                    // value读取
                    valueObj = readValue(protocol, tmap.valueType, valueSrcType);
                    method.invoke(map, keyObj, valueObj);
                }
                protocol.readMapEnd();
                return map;
            }
            case TType.SET: {
                // set的元素类型
                ParameterizedType srcType = (ParameterizedType) typeParam;
                Type elemSrcType = srcType.getActualTypeArguments()[0];
                // 创建set
                Class<?> clazz = (Class<?>) srcType.getRawType();
                if (clazz == Set.class) {
                    clazz = HashSet.class;
                }
                Object set = clazz.newInstance();
                // 读取set
                Object elemObj = null;
                Method method = clazz.getMethod("add", Object.class);
                TSet tset = protocol.readSetBegin();
                for (int i = 0; i < tset.size; i++) {
                    elemObj = readValue(protocol, tset.elemType, (Class<?>) elemSrcType);
                    method.invoke(set, elemObj);
                }
                protocol.readSetEnd();
                return set;
            }
            default:
                return null;
        }
    }
}
