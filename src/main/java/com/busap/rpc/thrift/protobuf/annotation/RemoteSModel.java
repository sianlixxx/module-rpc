package com.busap.rpc.thrift.protobuf.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.busap.rpc.thrift.common.SerializerMode;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RemoteSModel {
    SerializerMode SerializerMode() default SerializerMode.Auto;
}
