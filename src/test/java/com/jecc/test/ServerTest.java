package com.jecc.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jecc.thrift.service.UserService;

public class ServerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext-server-thrift.xml");
		UserService userService = (UserService) applicationContext
				.getBean(UserService.class);
		System.out.println(userService);
		System.out.println(userService.testSpring("lidong"));
	}

}
