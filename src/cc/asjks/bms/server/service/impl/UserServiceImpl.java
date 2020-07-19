package cc.asjks.bms.server.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.asjks.bms.server.mapper.*;
import cc.asjks.bms.server.model.*;
import cc.asjks.bms.server.service.*;
import cc.asjks.bms.server.util.*;

@Service("userService")
public class UserServiceImpl  implements UserService{
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	UserService userService;
	 
	@Override
	public List<User> queryAllUser() {
		// 查询所有
		return userMapper.queryAllUser();
	}

	@Override
	public int deleteUserById(Map<String, Object> map) {
		// 删除
		int id=Integer.valueOf(String.valueOf(map.get("uid")));
		return userMapper.deleteUserById(id);
	}

	@Override
	public int addUser(Map<String, Object> map) {
		// 添加
		String uname=String.valueOf(map.get("username"));
		String pswd=String.valueOf(map.get("password"));
		//String role=String.valueOf(map.get("role"));
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String regTime=sdf.format(new Date());
		Md5Hash md5 = new Md5Hash(uname,pswd);
		 
		User u=new User();
		u.setUsername(uname);	 
	    u.setPassword(md5.toString());
	    u.setRole("0");
	    u.setRegTime(regTime);
		User temp=userMapper.queryUserByUser(u);
		if (temp!=null) {
			return 0;
		}else{
			return userMapper.addUser(u);
		}
	}

	@Override
	public User login(Map<String, Object> map) {
		//登录
		String user=String.valueOf(map.get("username"));
		String pswd=String.valueOf(map.get("password"));
		Md5Hash md5 = new Md5Hash(user,pswd);
		User u=new User(user,md5.toString());
		//User u=new User(user,pswd);
		User u1=userMapper.login(u);
		return u1;
	}

	@Override
	public User queryUserById(Map<String, Object> map) {
		// 查询用户
		int id=Integer.valueOf(String.valueOf(map.get("uid")));
		return userMapper.queryUserById(id);
	}


	@Override
	public int updateInfoById(Map<String, Object> map) {
		//更新
		int id=Integer.valueOf(String.valueOf(map.get("uid")));
		String name=String.valueOf(map.get("trueName"));
		String tel=String.valueOf(map.get("tel"));
		 
		User u=new User(id,name,tel);		 
		 
		User temp=userMapper.queryUserByUser(u);
		if (temp!=null) {
			return 0;
		}else{
			return userMapper.updateInfoById(u);
		}
	}
	
	//验证token
	@Override
	public int verifyToken(Map<String, Object> map,int needRole) {
		String token=String.valueOf(map.get("token"));
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String nowTime=sdf.format(new Date());
		String tokenDecodeString=AES.AESDecode(nowTime, token);
		if(tokenDecodeString==null) {
			return -100;
		}
		Map<String, Object> tokenMap=MapJsonInterconversion.Json2Map(tokenDecodeString);
		
		String user=String.valueOf(tokenMap.get("username"));
		//String pass=String.valueOf(tokenMap.get("password"));
		//String uid=String.valueOf(tokenMap.get("uid"));
		
		User u1=new User();
		u1.setUsername(user);
		User u2=userMapper.queryUserByUser(u1);
		int role=Integer.valueOf(String.valueOf(u2.getRole()));
		/*if(needRole!=0) {
			if(!(role>=needRole)) {
				System.out.println(nowTime+" [DEBUG][ERROR] 权限不足  需要:"+needRole+" 当前:"+role);
				return -100;
			}
		}*/
		if(!(role>=needRole)) {
			System.out.println(nowTime+" [DEBUG][ERROR] 权限不足  需要:"+needRole+" 当前:"+role);
			return -100;
		}else {
			System.out.println(nowTime+" [DEBUG][Info] 权限验证通过  需要:"+needRole+" 当前:"+role);
		}
		/*String username=String.valueOf(u2.getUsername());
		String password=String.valueOf(u2.getPassword());
		int uid=Integer.valueOf(String.valueOf(u2.getUid()));*/
		String tokenOriginal="{\"username\":\""+u2.getUsername()+"\",\"password\":\""+u2.getPassword()+"\",\"uid\":\""+u2.getUid()+"\"}";
		
		//Md5Hash md5 = new Md5Hash(password,nowTime);
		if(token.equals(AES.AESEncode(nowTime, tokenOriginal))) {
			return u2.getUid();
		}else {
			return -100;
		}
	}
	
	//通过用户名查找用户信息
	@Override
	public User queryUserByUser(Map<String, Object> map) {
		String user=String.valueOf(map.get("username"));
		User u1=new User();
		u1.setUsername(user);
		return userMapper.queryUserByUser(u1);
	}
	//封禁用户/伪删除
	@Override
	public int banUserById(Map<String, Object> map) {
		int id=Integer.valueOf(String.valueOf(map.get("uid")));
		return userMapper.banUserById(id);
	}
	//查询用户权限
	@Override
	public int queryUserRoleById(Map<String, Object> map) {
		int id=Integer.valueOf(String.valueOf(map.get("id")));
		return userMapper.queryUserRoleById(id);
	}
	
	public void addDeposit(int id) {
		userMapper.addDeposit(id);
	}
}
