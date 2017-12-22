package com.jecc.thrift.controller;

import org.springframework.stereotype.Controller;

import com.busap.rpc.thrift.protobuf.annotation.Reference;
import com.jecc.thrift.service.UserService;

@Controller
public class ControllerUser {

	@Reference(version = "1.2", interfaceClass = UserService.class)
	private UserService userService;
	
	public void  index() {
		userService.testSpring("hello,jecc");
	}

}
