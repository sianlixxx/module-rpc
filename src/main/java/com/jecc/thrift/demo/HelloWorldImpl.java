package com.jecc.thrift.demo;

import org.apache.thrift.TException;

import com.jecc.thrift.demo.HelloWorldService.Iface;

/**
 * 
 */
public class HelloWorldImpl implements Iface {

	@Override
	public String sayHello(String username) throws TException {
		return "Hi," + username + " welcome to jecc";
	}

	@Override
	public int save(String username, String passWord) throws TException {
		return 20;
	}

	@Override
	public String login(String username, String passWord) throws TException {
		return "Hi," + username + " login success";
	}


}