package com.tianrun.finance.pro1.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrun.finance.pro1.pojo.UserInfo;
import com.tianrun.finance.pro1.user.dao.UserDao;

@Service
public class UserService {
	
	@Autowired
    private UserDao userDao;
	
	/**
	 * 用户登录验证，返回id
	 * @param user
	 * @return
	 */
    public String checkUser(UserInfo user){
    	return userDao.checkUser(user);
    }
    
    public boolean updatePwd(UserInfo user){
    	if (1==userDao.updatePwd(user)) 
    		return Boolean.TRUE;
    	else 
    		return Boolean.FALSE;
    }
    
    public boolean addUser(UserInfo user){
    	if (1==userDao.addUser(user)){
    		return Boolean.TRUE;
    	} else {
    		return Boolean.FALSE;
    	}
    }
}
