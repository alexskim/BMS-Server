package cc.asjks.bms.server.controller.api;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import cc.asjks.bms.server.mapper.*;
import cc.asjks.bms.server.model.*;
import cc.asjks.bms.server.service.*;
import cc.asjks.bms.server.util.*;
@RestController
@RequestMapping("api")
public class ApiController{
	@RequestMapping("")
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("api");
		return mav;
	}
	@Autowired
	MoneyMapper moneyMapper;
	@Autowired
	MoneyService moneyService;
	@Autowired
	UserMapper userMapper;
	@Autowired
	UserService userService;
	@Autowired
	DetailsMapper detailsMapper;
	@Autowired
	DetailsService detailsService;
	@Autowired
	AdMapper adMapper;
	@Autowired
	AdService adService;

	//登录
	//客户端已实现
	//Request:{"username":"username","password":"password"}
	//Response:{"result":"1","data":data,"token":"token"} or {"result":"0"}
	//data:{"uid":uid,"username":"username","password":"password","role":"role","trueName":trueName,"tel":tel,"regTime":"regTime"}
	@RequestMapping(value = "/login", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public Object login(@RequestBody Map<String, Object> map){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String tokenTime=sdf.format(new Date());
		Map<String, Object> temp=new HashMap<>();
		User u1=userService.login(map);
		if(u1!=null){
			String tokenOriginal="{\"username\":\""+u1.getUsername()+"\",\"password\":\""+u1.getPassword()+"\",\"uid\":\""+u1.getUid()+"\"}";
			String token=AES.AESEncode(tokenTime, tokenOriginal);
			temp.put("token",token);
			int uid=userService.verifyToken(temp,0);
			if(uid!=-100) {
				temp.put("result", "1");
				temp.put("data", u1);
				temp.put("token",token);
			}else {
				temp.put("result", "0");
				temp.remove("token");
				temp.put("msgerr", "用户已注销");
			}
		}else{
			temp.put("result", "0");
			temp.put("msgerr", "账号或密码错误");
		}
		return temp;
	}
	
	//注册
	//客户端已实现
	//Request:{"username":"username","password":"password"}
	//Response:{"result":"1"} or {"result":"0"}
	@RequestMapping(value = "/register", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public Object addUser(@RequestBody Map<String, Object> map)	{
		Map<String, Object> m=new HashMap<>();
		int i=userService.addUser(map);
		if(i==1){
			User u1=userService.queryUserByUser(map);
			m.put("result", "1");
			map.put("uid", u1.getUid());
			
			userService.addDeposit(Integer.valueOf(String.valueOf(map.get("uid"))));
		}else{
			m.put("result", "0");
			m.put("msgerr", "用户已存在");
		}
		return m;
	}
	 
	//查询所有用户
	//Request:{"token":"token"}
	//Response:{"result":"1","data":[array]} or {"result":"0"}
	//array:data
	@RequestMapping(value = "/queryAllUser", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public Object queryAllUser(@RequestBody Map<String, Object> map) {
		int uid=userService.verifyToken(map,1);
		if(uid!=-100) {
			Map<String, Object> m=new HashMap<>();
			List<User> list=userService.queryAllUser();
			if(list!=null){
				m.put("result", "1");
				m.put("data", list);
			}else{
				m.put("result", "0");
				m.put("msgerr", "无用户");
			}
			return m;
		}else {
			Map<String, Object> m=new HashMap<>();
			m.put("result", "0");
			m.put("msgerr", "权限不足");
			return m;
		}
	}
	
	//修改用户信息
	//客户端已实现
	//Request:{"token":"token","trueName":"trueName","tel":"tel"}
	//Response:{"result":"1"} or {"result":"0"}
	@RequestMapping(value = "/updateInfo", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public Object updateInfoById(@RequestBody Map<String, Object> map)	{
		Map<String, Object> m=new HashMap<>();
		String trueName=String.valueOf(map.get("trueName"));
		String tel=String.valueOf(map.get("tel"));
		int uid=userService.verifyToken(map,0);
		if(uid!=-100) {
			Map<String, Object> m1=new HashMap<>();
			m1.put("uid", uid);
			m1.put("trueName", trueName);
			m1.put("tel",tel);
			int i=userService.updateInfoById(m1);
			if(i==1){
				m.put("result", "1");
			}else{
				m.put("result", "0");
				m.put("msgerr", "修改失败");
			}
			return m;
		}else {
			m.put("result", "0");
			m.put("msgerr", "权限不足");
			return m;
		}
	}
	//修改任意用户信息
	//Request:{"token":"token","uid":"uid","trueName":"trueName","tel":"tel"}
	//Response:{"result":"1"} or {"result":"0"}
	@RequestMapping(value = "/updateAnyInfo", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public Object updateAnyInfoById(@RequestBody Map<String, Object> map)	{
		Map<String, Object> m=new HashMap<>();
		String toUid=String.valueOf(map.get("uid"));
		String trueName=String.valueOf(map.get("trueName"));
		String tel=String.valueOf(map.get("tel"));
		int uid=userService.verifyToken(map,1);
		if(uid!=-100) {
			Map<String, Object> m1=new HashMap<>();
			m1.put("uid", toUid);
			m1.put("trueName", trueName);
			m1.put("tel",tel);
			int i=userService.updateInfoById(m1);
			if(i==1){
				m.put("result", "1");
			}else{
				m.put("result", "0");
				m.put("msgerr", "修改失败");
			}
			return m;
		}else {
			m.put("result", "0");
			m.put("msgerr", "权限不足");
			return m;
		}
	}
	//删除用户
	//Request:{"token":"token"}
	//Response:{"result":"1"} or {"result":"0"}
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public Object deleteUserById(@RequestBody Map<String, Object> map)	{
		Map<String, Object> m=new HashMap<>();
		int uid=userService.verifyToken(map,0);
		if(uid!=-100&&uid!=1) {
			Map<String, Object> m1=new HashMap<>();
			m1.put("uid", uid);
			//int i=userService.deleteUserById(m1);//真删除
			int i=userService.banUserById(m1);//伪删除
			if(i==1){
				m.put("result", "1");
			}else{
				m.put("result", "0");
				m.put("msgerr", "删除失败");
			}
			return m;
		}else {
			m.put("result", "0");
			m.put("msgerr", "权限不足");
			return m;
		}
	}
	
	//删除任意用户
	//Request:{"token":"token","uid":"uid"}
	//Response:{"result":"1"} or {"result":"0"}
	@RequestMapping(value = "/deleteAnyUser", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public Object deleteAnyUserById(@RequestBody Map<String, Object> map)	{
		Map<String, Object> m=new HashMap<>();
		int toUid=Integer.valueOf(String.valueOf(map.get("uid")));
		int uid=userService.verifyToken(map,1);
		if(uid!=-100&&toUid!=1) {
			Map<String, Object> m1=new HashMap<>();
			m1.put("uid", toUid);
			//int i=userService.deleteUserById(m1);//真删除
			int i=userService.banUserById(m1);//伪删除
			if(i==1){
				m.put("result", "1");
			}else{
				m.put("result", "0");
				m.put("msgerr", "删除失败");
			}
			return m;
		}else {
			m.put("result", "0");
			m.put("msgerr", "权限不足");
			return m;
		}
	}

	//查询余额
	//客户端已实现
	//Request:{"token":"token","currency":"USD"}
	//Response:{"result":"1","currency":"currency","amount":0} or {"result":"0"}
	@RequestMapping(value="queryBalance", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public Object queryBalanceById(@RequestBody Map<String, Object> map){
		Map<String, Object> m=new HashMap<>();
		String currency=String.valueOf(map.get("currency"));
		int uid=userService.verifyToken(map,0);
		if(uid!=-100) {
			map.put("uid", uid);
			Double balance=moneyService.queryBlanaceById(map);
			if(balance!=null){
				m.put("result", "1");
				m.put("currency", currency);
				m.put("amount", balance);
			}else{
				m.put("result", "0");
				m.put("msgerr", "查询失败");
			}
			return m;
		}else {
			m.put("result", "0");
			m.put("msgerr", "权限不足");
			return m;
		}
	}
	
	//转账
	//客户端已实现
	//Request:{"token":"token","currency":"USD","to":"16","amount":"1000"}
	//Response:{"result":"1"} or {"result":"0"}
	@RequestMapping(value="transferAccounts", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public Object transferAccountsById(@RequestBody Map<String, Object> map){
		Map<String, Object> m=new HashMap<>();
		int uid=userService.verifyToken(map,0);
		if(uid!=-100) {
			Map<String, Object> m1=new HashMap<>();
			m1.put("id", String.valueOf(map.get("to")));
			int i2=userService.queryUserRoleById(m1);
			if(i2!=-1) {
				map.put("uid", uid);
				int i=moneyService.transferAccountsById(map);
				if(i==1){
					m.put("result", "1");
				}else{
					m.put("result", "0");
					m.put("msgerr", "转账失败");
				}
				return m;
			}else {
				m.put("result", "0");
				m.put("msgerr", "对方账户已注销");
				return m;
			}
		}else {
			m.put("result", "0");
			m.put("msgerr", "权限不足");
			return m;
		}
	}
	
	//存款
	//客户端已实现
	//Request:{"token":"token","currency":"USD","amount":"1000"}
	//Response:{"result":"1"} or {"result":"0"}
	@RequestMapping(value="deposit", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public Object depositById(@RequestBody Map<String, Object> map){
		Map<String, Object> m=new HashMap<>();
		int uid=userService.verifyToken(map,0);
		if(uid!=-100) {
			map.put("uid", uid);
			int i=moneyService.depositById(map);
			if(i==1){
				m.put("result", "1");
			}else{
				m.put("result", "0");
				m.put("msgerr", "存款失败");
			}
			return m;
		}else {
			m.put("result", "0");
			m.put("msgerr", "权限不足");
			return m;
		}
	}
	
	//取款
	//客户端已实现
	//Request:{"token":"token","currency":"USD","amount":"1000"}
	//Response:{"result":"1"} or {"result":"0"}
	@RequestMapping(value="withdraw", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public Object withdrawById(@RequestBody Map<String, Object> map){
		Map<String, Object> m=new HashMap<>();
		int uid=userService.verifyToken(map,0);
		if(uid!=-100) {
			map.put("uid", uid);
			int i=moneyService.withdrawById(map);
			if(i==1){
				m.put("result", "1");
			}else{
				m.put("result", "0");
				m.put("msgerr", "取款失败");
			}
			return m;
		}else {
			m.put("result", "0");
			m.put("msgerr", "权限不足");
			return m;
		}
	}
	
	//查询所有交易明细
	//Request:{"token":"token"}
	//Response:{"result":"1","data":[array]} or {"result":"0"}
	//array:{"id":id,"uid":uid,"date":"date","toUid":toUid,"amount":0,"currency":"currency","type":"type"}
	@RequestMapping(value="queryAllDetails", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public Object queryAllDetails(@RequestBody Map<String, Object> map) {
		int uid=userService.verifyToken(map,1);
		if(uid!=-100) {
			Map<String, Object> m=new HashMap<>();
			List<Details> list=detailsService.queryAllDetails();
			if(list!=null){
				m.put("result", "1");
				m.put("data", list);
			}else{
				m.put("result", "0");
				m.put("msgerr", "交易明细为空");
			}
			return m;
		}else {
			Map<String, Object> m=new HashMap<>();
			m.put("result", "0");
			m.put("msgerr", "权限不足");
			return m;
		}
	}
	
	//查询交易明细
	//Request:{"token":"token"}
	//Response:{"result":"1","data":[array]} or {"result":"0"}
	//array:{"id":id,"uid":uid,"date":"date","toUid":toUid,"amount":0,"currency":"currency","type":"type"}
	@RequestMapping(value="queryDetails", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public Object queryDetails(@RequestBody Map<String, Object> map) {
		int uid=userService.verifyToken(map,0);
		if(uid!=-100) {
			Map<String, Object> m=new HashMap<>();
			map.put("uid", uid);
			List<Details> list=detailsService.queryDetails(map);
			if(list!=null){
				m.put("result", "1");
				m.put("data", list);
			}else{
				m.put("result", "0");
				m.put("msgerr", "交易明细为空");
			}
			return m;
		}else {
			Map<String, Object> m=new HashMap<>();
			m.put("result", "0");
			m.put("msgerr", "权限不足");
			return m;
		}
	}
	
	//列出广告
	//Request:{"token":"token"}
	//Response:{"result":"1","data":[array]} or {"result":"0"}
	//array:{"id":id,"contact":"contact","content":"content","img":img,"begin":begin,"end":end}
	@RequestMapping(value="queryAllAd", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public Object queryAllAd(@RequestBody Map<String, Object> map) {
		int uid=userService.verifyToken(map,1);
		if(uid!=-100) {
			Map<String, Object> m=new HashMap<>();
			List<Ad> list=adService.queryAllAd();
			if(list!=null){
				m.put("result", "1");
				m.put("data", list);
			}else{
				m.put("result", "0");
				m.put("msgerr", "广告不存在");
			}
			return m;
		}else {
			Map<String, Object> m=new HashMap<>();
			m.put("result", "0");
			m.put("msgerr", "权限不足");
			return m;
		}
	}
	
	//列出有效广告
	//Request:{"token":"token"}
	//Response:{"result":"1","data":[array]} or {"result":"0"}
	//array:{"id":id,"contact":"contact","content":"content","img":img,"begin":begin,"end":end}
	@RequestMapping(value="queryAllAdByTime", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public Object queryAllAdByTime(@RequestBody Map<String, Object> map) {
		int uid=userService.verifyToken(map,0);
		if(uid!=-100) {
			Map<String, Object> m=new HashMap<>();
			List<Ad> list=adService.queryAllAdByTime(new Date().getTime());
			if(list!=null){
				m.put("result", "1");
				m.put("data", list);
			}else{
				m.put("result", "0");
				m.put("msgerr", "广告不存在");
			}
			return m;
		}else {
			Map<String, Object> m=new HashMap<>();
			m.put("result", "0");
			m.put("msgerr", "权限不足");
			return m;
		}
	}
}
