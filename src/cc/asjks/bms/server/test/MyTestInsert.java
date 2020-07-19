package cc.asjks.bms.server.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
 

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cc.asjks.bms.server.model.User;
import cc.asjks.bms.server.service.UserService;

public class MyTestInsert {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService=(UserService)context.getBean("userService");
 
		 Map<String, Object> map= new HashMap<String, Object>();
		
		map.put("username","test1");
		map.put("password","123456");
	 
		userService.addUser(map);  
		
		 
		 
		
		List<User> userList=userService.queryAllUser();
 
		System.out.println(userList.size());
		 for(User u:userList){
			System.out.println(u.getUsername()+"-"+u.getRole()+"-"+u.getTrueName());
		} 
		
		 
		 
	}

}
