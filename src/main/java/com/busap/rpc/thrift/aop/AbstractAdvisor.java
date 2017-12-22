package com.busap.rpc.thrift.aop;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

import org.aopalliance.intercept.MethodInvocation;

import com.busap.rpc.thrift.protobuf.annotation.RemoteService;

public class AbstractAdvisor implements Proceed{

	public static final int AFTER_ORDER = 0;

	public static final int AROUND_ORDER = 1;

	public static final int BEFORE_ORDER = 2;

	private Pattern pointCutPattern;

	protected String pointCut;

	protected Object aspectObject;

	protected Method aspectMethod;

	protected int order;

	public AbstractAdvisor(Object aspectObject, Method aspectMethod) {
		pointCut = aspectObject.getClass().getAnnotation(RemoteService.class).interfaceName();
		pointCutPattern = Pattern.compile(pointCut);
		this.aspectObject = aspectObject;
		this.aspectMethod = aspectMethod;
	}

	public boolean isMatch(Class<?> matchClass, Method method) {
		return pointCutPattern.matcher(matchClass.getName() + "." + method.getName()).find();
	}

	public int getOrder() {
		return order;
	}

	@Override
	public Object proceed(MethodInvocation methodInvocation) throws Throwable {
		return null;
	}
}
