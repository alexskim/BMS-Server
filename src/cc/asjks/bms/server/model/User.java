package cc.asjks.bms.server.model;

import java.sql.*;

public class User
{
	private Integer uid;
    private String username;
    private String password;
  
    private String role;
    private String trueName;
    private String tel;
    private String regTime;
   
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
  
	 
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}


	public User(Integer uid, String trueName, String tel) {
		super();
		this.uid = uid;
		this.trueName = trueName;
		this.tel = tel;
	}


	public User(String username, String password, String role, String regTime) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.regTime = regTime;
	}
	public User(String username, String password, String role, String trueName, String tel, String regTime) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.trueName = trueName;
		this.tel = tel;
		this.regTime = regTime;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getRegTime() {
		return regTime;
	}
	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}
    
	
	 
	 
	 

	
	
}
