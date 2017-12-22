package com.busap.rpc.thrift.protobuf.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RemoteMethod{
    String name() default "";
    boolean isOneWay() default false;
    String description() default "";
}
