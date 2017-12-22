package com.busap.rpc.thrift.aop;

import org.aopalliance.intercept.MethodInvocation;

public interface Proceed {

	Object proceed(MethodInvocation methodInvocation) throws Throwable;
}
