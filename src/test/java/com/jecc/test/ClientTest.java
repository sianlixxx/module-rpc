package com.jecc.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ImportResource;

import com.busap.rpc.thrift.beans.context.annotation.JeccComponentScan;
import com.busap.rpc.thrift.protobuf.annotation.Reference;
import com.jecc.thrift.service.UserService;

public class ClientTest {

	public static void main(String[] args) throws Exception {
	    /* AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
	     context.register(TestBean.class);
	     context.refresh();*/
//		ApplicationContext context = new ClassPathXmlApplicationContext(
//				"applicationContext-client-thrift.xml");
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(TestBean.class);
		context.refresh();
		 TestBean testBean = context.getBean(TestBean.class);
		UserService userService = testBean.getUserService();
		System.out.print(userService.testSpring("hello lidong"));

	}

	private static class ParentBean  {

		@Reference(version = "1.2",interfaceClass=UserService.class)
		private UserService userService;

	}
	@ImportResource("applicationContext-client-thrift.xml")
	@JeccComponentScan(basePackageClasses = ClientTest.class)
	private static class TestBean extends ParentBean {

		private UserService userService;

		public UserService getUserService() {
			return userService;
		}
		
		@Reference(version = "1.2",interfaceClass=UserService.class)
		public void setUserService(UserService userService) {
			this.userService = userService;
		}

		@Autowired
		private ApplicationContext applicationContext;

	}

}
