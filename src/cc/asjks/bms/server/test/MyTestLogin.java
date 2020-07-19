package cc.asjks.bms.server.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cc.asjks.bms.server.model.User;
import cc.asjks.bms.server.service.UserService;

public class MyTestLogin {

	public static void main(String[] args) {
	 
				ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
				UserService userService=(UserService)context.getBean("userService");
		 
				 Map<String, Object> map= new HashMap<String, Object>();				
				 map.put("username","user1");
				 map.put("password","123456");
				 
				 User user=userService.login(map);
				 if(user!=null)
					 System.out.println(user.getUid());
				 else
					 System.out.println("fail");
				 
				 
	}

}
