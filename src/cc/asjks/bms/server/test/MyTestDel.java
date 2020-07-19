package cc.asjks.bms.server.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cc.asjks.bms.server.model.User;
import cc.asjks.bms.server.service.UserService;

public class MyTestDel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService=(UserService)context.getBean("userService");
 
		Map<String, Object> map= new HashMap<String, Object>();
		
		map.put("uid",30 );
		 
	 
		int i=userService.deleteUserById(map);  
		 if(i>0)
			 System.out.println("success");
		 else
			 System.out.println("fail");
		 
		 
		
		List<User> userList=userService.queryAllUser();
 
		System.out.println(userList.size());
		 for(User u:userList){
			System.out.println(u.getUid()+"-"+u.getUsername()+"-"+u.getRole()+"-"+u.getTrueName());
		} 
	}

}
