package com.tianrun.article.restController;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

@RestController
public class ArticleManagerController {
	@RequestMapping(path="classes", method=RequestMethod.GET)
    public HttpEntity<String> getTaxInfos() {
		return new ResponseEntity<String>(JSON.toJSONString("123"), HttpStatus.OK);
    }
}
