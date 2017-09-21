package com.tianrun.user.dao;

import org.apache.ibatis.annotations.Mapper;

import com.tianrun.user.pojo.UserInfo;

@Mapper
public interface UserDao {
	
	/**
	 * 用户登录验证
	 * @param user
	 * @return
	 */
	public UserInfo checkUser(UserInfo user);
	
	public int updatePwd(UserInfo user);
	
	public int addUser(UserInfo user);
}
