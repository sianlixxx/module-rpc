package com.jecc.thrift.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;


import com.jecc.model.Person;


public interface UserService {

	
	String testParam(Person person, List<Person> list, Map<String, Person> map,
			Set<Person> set);

	
	String test(int age, String name, Person person);

	
	String test2(Person person1, Person person2);

	
	List<Person> testList(List<Person> list);

	
	Map<String, Person> testMap(Map<String, Person> map);

	
	Set<Person> testSet(Set<Person> set);

	
	String testParam2(BigDecimal big, Date date, UUID uuid, Double dob,
			Boolean bool);

	/**
	 * 测试非注解类调用Spring的注解类(普通类调用Mapper)
	 * 
	 * @param dob
	 * @param bool
	 * @return
	 */
	
	String testSpring(String test);

}
