package com.tianrun.finance.pro1.user.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tianrun.finance.pro1.HttpEntityPojo;
import com.tianrun.finance.pro1.pojo.UserInfo;
import com.tianrun.finance.pro1.user.service.UserService;

@RestController
public class LoginController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(path="userLogin", method=RequestMethod.GET)
    public HttpEntity<String> check(
    		HttpSession session,
            @RequestParam(value = "phone", required = true) String phone,
            @RequestParam(value = "pwd", required = true) String pwd
            ) {
		
		UserInfo user = new UserInfo();
		user.setPwd(pwd);
		user.setPhone(phone);
		String id = userService.checkUser(user);
		HttpEntityPojo o = new HttpEntityPojo();
		
		if (id == null){
			o.setCode("0");
			o.setMsg(HttpEntityPojo.MSG_FAIL);
			return new ResponseEntity<String>(o.toJSONString(), HttpStatus.OK);
		} else {
			o.setCode("1");
			o.setMsg(HttpEntityPojo.MSG_SUCCESS);
			o.setBusiData("userId", id);
			session.setAttribute("userId", id);
			session.setAttribute("phone", phone);
			return new ResponseEntity<String>(o.toJSONString(), HttpStatus.OK);
		}
    }
	
	
}
