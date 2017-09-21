package com.tianrun.finance.pro1.user.dao;

import org.apache.ibatis.annotations.Mapper;

import com.tianrun.finance.pro1.pojo.UserInfo;

@Mapper
public interface UserDao {
	
	/**
	 * 用户登录验证
	 * @param user
	 * @return
	 */
	public String checkUser(UserInfo user);
	
	public int updatePwd(UserInfo user);
	
	public int addUser(UserInfo user);
}
