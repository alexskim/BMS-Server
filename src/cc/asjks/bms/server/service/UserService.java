package cc.asjks.bms.server.service;

import java.util.List;
import java.util.Map;

import cc.asjks.bms.server.model.*;

public interface UserService {

	List<User> queryAllUser();
	int deleteUserById(Map<String, Object> map);
	int banUserById(Map<String, Object> map);
	int addUser(Map<String, Object> map);
	User login(Map<String, Object> map);
	int updateInfoById(Map<String, Object> map);
	User queryUserById(Map<String, Object> map);
	int verifyToken(Map<String, Object> map,int needRole);
	User queryUserByUser(Map<String, Object> map);
	void addDeposit(int id);
	int queryUserRoleById(Map<String, Object> map);
}
