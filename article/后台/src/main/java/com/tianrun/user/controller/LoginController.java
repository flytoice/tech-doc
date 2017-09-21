package com.tianrun.user.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tianrun.rest.HttpEntityPojo;
import com.tianrun.user.pojo.UserInfo;
import com.tianrun.user.service.UserService;

@RestController
public class LoginController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(path="userLogin", method=RequestMethod.GET)
    public HttpEntity<String> check(
    		HttpSession session,
            @RequestParam(value = "acc", required = true) String acc,
            @RequestParam(value = "pwd", required = true) String pwd
            ) {
		
		UserInfo user = new UserInfo();
		user.setPwd(pwd);
		user.setPhone(acc);
		user = userService.checkUser(user);
		HttpEntityPojo o = new HttpEntityPojo();
		
		if (user.getId() == null){
			o.setCode("0");
			o.setMsg(HttpEntityPojo.MSG_FAIL);
			return new ResponseEntity<String>(o.toJSONString(), HttpStatus.OK);
		} else {
			o.setCode("1");
			o.setMsg(HttpEntityPojo.MSG_SUCCESS);
			o.setBusiData("userId", user.getId());
			session.setAttribute("user",user);
			return new ResponseEntity<String>(o.toJSONString(), HttpStatus.OK);
		}
    }
	
	@RequestMapping(path="userLogout", method=RequestMethod.GET)
    public HttpEntity<String> logout(HttpSession session ) {

		session.removeAttribute("user");
		return new ResponseEntity<String>("{}", HttpStatus.OK);
    }
	
	
}
