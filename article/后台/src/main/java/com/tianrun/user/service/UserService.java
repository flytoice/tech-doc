package com.tianrun.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrun.user.dao.UserDao;
import com.tianrun.user.pojo.UserInfo;

@Service
public class UserService {
	
	@Autowired
    private UserDao userDao;
	
	/**
	 * 用户登录验证，返回id
	 * @param user
	 * @return
	 */
    public UserInfo checkUser(UserInfo user){
    	UserInfo u = userDao.checkUser(user);
    	if (u!=null){
    		user.setId(u.getId());
    		user.setStaffId(u.getStaffId());
        	user.setName(u.getName());
    	}
    	return user;
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
