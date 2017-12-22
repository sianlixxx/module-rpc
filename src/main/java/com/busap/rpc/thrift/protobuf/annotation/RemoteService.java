package com.busap.rpc.thrift.protobuf.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RemoteService {
	/**
	 * interfaceName必须填写
	 * @return
	 */
    String interfaceName();
    
    Class<?> interfaceClass() default void.class;
    /**
     * 服务的版本
     * @return
     */
    int version() default 1;
}
