package com.jecc.thrift.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.busap.rpc.thrift.protobuf.annotation.RemoteMethod;
import com.busap.rpc.thrift.protobuf.annotation.RemoteService;
import com.jecc.model.Person;
import com.jecc.thrift.service.UserService;

@RemoteService(interfaceName = "userServiceImpl", interfaceClass = UserService.class)
public class UserServiceImpl implements UserService {

	@Override
	@RemoteMethod
	public String testParam(Person person, List<Person> list,
			Map<String, Person> map, Set<Person> set) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RemoteMethod
	public String test(int age, String name, Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RemoteMethod
	public String test2(Person person1, Person person2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RemoteMethod
	public List<Person> testList(List<Person> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RemoteMethod
	public Map<String, Person> testMap(Map<String, Person> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RemoteMethod
	public Set<Person> testSet(Set<Person> set) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RemoteMethod
	public String testParam2(BigDecimal big, Date date, UUID uuid, Double dob,
			Boolean bool) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RemoteMethod
	public String testSpring(String test) {

		return "你好:" + test;
	}

}
