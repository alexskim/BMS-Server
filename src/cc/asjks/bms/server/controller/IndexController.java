package cc.asjks.bms.server.controller;


import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cc.asjks.bms.server.mapper.AdMapper;
import cc.asjks.bms.server.mapper.DetailsMapper;
import cc.asjks.bms.server.mapper.MoneyMapper;
import cc.asjks.bms.server.mapper.UserMapper;
import cc.asjks.bms.server.model.*;
import cc.asjks.bms.server.service.*;
import cc.asjks.bms.server.util.*;

import javax.servlet.http.*;

@Controller
@RequestMapping("")
public class IndexController {
	@Autowired
	MoneyService moneyService;
	@Autowired
	UserService userService;
	@Autowired
	DetailsService detailsService;
	@Autowired
	AdService adService;
 

	@RequestMapping("")
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}
	 
	//注销
	@RequestMapping("logout")
	public ModelAndView logout(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		HttpSession session=request.getSession();
		session.setAttribute("role", "");
		mav.setViewName("login");
		return mav;
	}

	@RequestMapping("view")
	public ModelAndView index3(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}
	//登录
	@RequestMapping("login")
	public String login(HttpServletRequest request){
		HttpSession session=request.getSession();
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("username", request.getParameter("username"));
		map.put("password", request.getParameter("password"));
		User user=userService.login(map);
		String url="view";
		if(user!=null){
			String role=user.getRole();
			if(role.equals("1")){
				url="index";
				session.setAttribute("role",role);
			}  
			else{
				session.setAttribute("what","1");
				session.setAttribute("message", "您不是管理用户");
			}
		}else{
			session.setAttribute("what","1");
			session.setAttribute("message", "账户或密码错误");
		}
		return "redirect:"+url;
	}
	//查询所有用户
	@RequestMapping("index")
	public ModelAndView index1(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		List<User> list=userService.queryAllUser();
		mav.addObject("userList",list);
		return mav;
	}


	//删除
	@RequestMapping(value="deleteUserById",method = RequestMethod.POST)
	public String deleteUserById(HttpServletRequest request){
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("uid",request.getParameter("uid"));
		System.out.println(request.getParameter("uid"));
		HttpSession session=request.getSession();
		int i=userService.deleteUserById(map);
		if (i>0){
			System.out.println("delete success");
		}else{
			session.setAttribute("message", "失败");
		}
		return "redirect:index";
	}

	 
	//注册/添加用户
	@RequestMapping(value="addUser",method = RequestMethod.POST)
	public String addUser(HttpServletRequest request){
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		HttpSession session=request.getSession();
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("username",request.getParameter("username"));
		map.put("password",request.getParameter("password"));
		//map.put("role",request.getParameter("role"));
		if (userService.addUser(map)==0) {
			session.setAttribute("message", "失败");
		}
		User u1=userService.queryUserByUser(map);
		userService.addDeposit(u1.getUid());
		return "redirect:index";
	}
	//更新
	@RequestMapping(value="updateInfoById",method = RequestMethod.POST)
	public String updateInfoById(HttpServletRequest request){
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		HttpSession session=request.getSession();
		
		 
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("uid",request.getParameter("uid"));
		map.put("trueName",request.getParameter("trueName"));
		map.put("tel",request.getParameter("tel"));
		 
	 
		int i=userService.updateInfoById(map);
		
		if (i<=0) {
			session.setAttribute("message", "失败");
		}
		return "redirect:index";
	}
	
	//更新余额
	@RequestMapping(value="updateMoneyById",method = RequestMethod.POST)
	public String updateMoneyById(HttpServletRequest request){
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		HttpSession session=request.getSession();
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("uid",request.getParameter("uid"));
		map.put("CNY",request.getParameter("CNY"));
		map.put("USD",request.getParameter("USD"));
		int i=moneyService.updateMoneyById(map);
		if (i<=0) {
			session.setAttribute("message", "失败");
		}
		return "redirect:money";
	}
	
	//显示广告
	@RequestMapping("ad")
	public ModelAndView ad(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("ad");
		List<Ad> list=adService.queryAllAd();
		mav.addObject("adList",list);
		return mav;
	}
 
	
	//更新广告
	@RequestMapping(value="updateAdById",method = RequestMethod.POST)
	public String updateAdById(HttpServletRequest request){
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		HttpSession session=request.getSession();
		String begin=DateStampInterconversion.dateToStamp(request.getParameter("begin").replace('T',' '));
		String end=DateStampInterconversion.dateToStamp(request.getParameter("end").replace('T',' '));
		System.out.println("begin:"+begin);
		System.out.println("end:"+end);
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("id",request.getParameter("id"));
		map.put("contact",request.getParameter("contact"));
		map.put("content",request.getParameter("content"));
		map.put("img",request.getParameter("img"));
		map.put("begin",begin);
		map.put("end",end);
		int i=adService.updateAd(map);
		if (i<=0) {
			session.setAttribute("message", "失败");
		}
		return "redirect:ad";
	}
	
	//删除广告
	@RequestMapping(value="deleteAdById",method = RequestMethod.POST)
	public String deleteAdById(HttpServletRequest request){
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("id",request.getParameter("id"));
		HttpSession session=request.getSession();
		int i=adService.deleteAdById(map);
		if (i>0){
			System.out.println("delete success");
		}else{
			session.setAttribute("message", "失败");
		}
		return "redirect:ad";
	}
	
	//添加广告
	@RequestMapping(value="addAd",method = RequestMethod.POST)
	public String addAd(HttpServletRequest request){
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String begin=DateStampInterconversion.dateToStamp(request.getParameter("begin").replace('T',' '));
		String end=DateStampInterconversion.dateToStamp(request.getParameter("end").replace('T',' '));
		HttpSession session=request.getSession();
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("contact",request.getParameter("contact"));
		map.put("content",request.getParameter("content"));
		map.put("begin",begin);
		map.put("end",end);
		if (adService.addAd(map)==0) {
			session.setAttribute("message", "失败");
		}
		return "redirect:ad";
	}
	
	//显示余额
	@RequestMapping("money")
	public ModelAndView money(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("money");
		List<Money> list=moneyService.queryAllBlanace();
		mav.addObject("moneyList",list);
		return mav;
	}
	
	//显示交易明细
	@RequestMapping("details")
	public ModelAndView details(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("details");
		List<Details> list=detailsService.queryAllDetails();
		mav.addObject("detailsList",list);
		return mav;
	}
}
