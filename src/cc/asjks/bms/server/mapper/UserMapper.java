package cc.asjks.bms.server.mapper;
 
import java.util.List;

import javax.jws.soap.SOAPBinding.Use;

import cc.asjks.bms.server.model.*;
 
public interface UserMapper {
	/**
	 * 查询所有用户信息
	 */
	public List<User> queryAllUser();
	/**
	 * 根据ID删除用户
	 * @param id
	 * @return
	 */
	public int deleteUserById(int id);
	public int addUser(User user);
	/**
	 * 查询用户
	 * @param user
	 * @return
	 */
	public User queryUserByUser(User user);
	 /**
	  * 登录
	  * @param user
	  * @return
	  */
	public User login(User user);
	 
	public int updateInfoById(User user);
	/**
	 * 根据id查询用户
	 * @param id
	 * @return
	 */
	public User queryUserById(int id);
	
	public void addDeposit(int id);
 
	public int banUserById(int id);
	
	public int queryUserRoleById(int id);
	 
}