package com.busap.rpc.thrift.protobuf.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface Reference {

    Class<?> interfaceClass() default void.class;

    String interfaceName() default "";

    String version() default "";

    String proxy() default "";

    boolean async() default false;

    int timeout() default 0;
    
    String consumer() default "";
    
    String[] registry() default {};
    String application() default "";

}
