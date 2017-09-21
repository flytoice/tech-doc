package com.tianrun.finance.pro1.pojo;

public class UserInfo {
	private String id;
	private String pwd;
	private String staffId;
	private String phone;
	
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
}
