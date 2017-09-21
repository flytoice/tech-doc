package com.tianrun.finance.pro1.sms.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tianrun.finance.pro1.user.service.UserService;

@RestController
public class SmsController {
	@Autowired
	UserService userService;
	
	@RequestMapping(path="/sms", method=RequestMethod.GET)
	public HttpEntity<String> sendCheckCode(
			HttpSession session,
    		@RequestParam(value = "phone", required = true) String phone) {
		
		//生成验证码
		String code = "123";
		//存储验证码
		session.setAttribute("smsCode", code);
		session.setAttribute("phone", phone);
		//发送到用户手机
        return new ResponseEntity<String>("已发送", HttpStatus.OK);
    }
	
	
	
}
