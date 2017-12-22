package com.busap.rpc.thrift.protobuf.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.busap.rpc.thrift.common.FieldType;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RemoteSMember {
    /**
     * <pre>
     * Specifying Field Rules to <code>required</code> or <code>optional</code> 
     * default is false. <code>optional</code>
     * </pre>
     * 
     * @return Specifying Field Rules
     */
    boolean required() default false;

    /**
     * Set field order. It starts at 1;
     * 
     * @return field order.
     */
    short order();

    /**
     * @return field type
     */
    FieldType fieldType();
    
    /**
     * @return description to the field
     */
    String description() default "";
}
