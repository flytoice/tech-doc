package com.tianrun.user.pojo;

public class UserInfo {
	private String id;
	private String pwd;
	private String staffId;
	private String phone;
	private String name;
	
	public String getStaffId() {
		return staffId;
	}
	public String getPhone() {
		return phone;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
