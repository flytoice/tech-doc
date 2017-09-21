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
public class UpdateController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(path="updPwd", method=RequestMethod.PUT)
    public HttpEntity<String> check(
    		HttpSession session,
            @RequestParam(value = "pwd1", required = true) String pwd1,
            @RequestParam(value = "pwd2", required = true) String pwd2
            ) {
		
		UserInfo user = new UserInfo();
		user.setId(session.getAttribute("userId").toString());
		user.setPwd(pwd1);
		user.setPhone(session.getAttribute("phone").toString());
		
		
		HttpEntityPojo o = new HttpEntityPojo();
		if (userService.updatePwd(user)){
			o.setCode("1");
			o.setMsg(HttpEntityPojo.MSG_SUCCESS);
			return new ResponseEntity<String>(o.toJSONString(), HttpStatus.OK);
		} else {
			o.setCode("0");
			o.setMsg(HttpEntityPojo.MSG_FAIL);
			return new ResponseEntity<String>(o.toJSONString(), HttpStatus.OK);
		}
    }
	
	@RequestMapping(path="addUser", method=RequestMethod.GET)
    public HttpEntity<String> add(
    		HttpSession session,
            @RequestParam(value = "staffId", required = true) String staffId,
            @RequestParam(value = "phone", required = true) String phone,
            @RequestParam(value = "pwd", required = true) String pwd
            ) {
		UserInfo user = new UserInfo();
		user.setStaffId(staffId);
		user.setPhone(phone);
		user.setPwd(pwd);
		HttpEntityPojo o = new HttpEntityPojo();
		if (userService.addUser(user)){
			o.setCode("1");
			o.setMsg(HttpEntityPojo.MSG_SUCCESS);
		} else {
			o.setCode("0");
			o.setMsg(HttpEntityPojo.MSG_FAIL);
		}
		return new ResponseEntity<String>(o.toJSONString(), HttpStatus.OK);
	}
	
	
}
