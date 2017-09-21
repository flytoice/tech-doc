package com.tianrun.artM.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.tianrun.HttpEntityJson;
import com.tianrun.artM.pojo.ArtClass;
import com.tianrun.artM.pojo.Article;
import com.tianrun.artM.pojo.L2Class;
import com.tianrun.artM.service.ArtService;

@RestController
public class DataController {
	
	@Autowired
    public Environment env;
	
	@Autowired
    private ArtService service;
	
	@RequestMapping(path="titles", method=RequestMethod.GET)
    public HttpEntity<String> getTitlesByType(
    		@RequestParam(value="id", required=true) String classId
            ) {
		
		List<Article> o = service.getTitleByType(classId);
		
		return new ResponseEntity<String>(JSONObject.toJSONString(o), HttpStatus.OK);
    }
	
	@RequestMapping(path="titlesByPage", method=RequestMethod.GET)
    public HttpEntity<String> getAll(
    		@RequestParam(value="id", required=true) String classId,
    		@RequestParam(value="pageNum", required=true) int pageNumber) {
		
		int pageSize = Integer.parseInt(env.getProperty("spring.pagesize"));
		
		List<Article> o = service.getTitleByTypeAndPage(classId,pageNumber,pageSize);
		PageInfo<Article> ctx = new PageInfo<Article>(o);

		return new ResponseEntity<String>(JSONObject.toJSONString(ctx), HttpStatus.OK);
    }
	
	@RequestMapping(path="title", method=RequestMethod.DELETE)
    public HttpEntity<String> delTitle(
    		@RequestParam(value="articleId", required=true) String artId,
    		@RequestParam(value="typeId", required=true) String typeId
    		) {
		HttpEntityJson entity = new HttpEntityJson();
		if (service.delArticle(artId,typeId)){
			entity.setSuccess();
		} else {
			entity.setFail();
		}
		return new ResponseEntity<String>(entity.toJSONString(), HttpStatus.OK);
    }
	
	@RequestMapping(path="title", method=RequestMethod.POST)
    public HttpEntity<String> edit(
    		@RequestParam(value="title", required=true) String title,
    		@RequestParam(value="articleId", required=true) String artId,
    		@RequestParam(value="typeId", required=true) String classId,
    		@RequestParam(value="data", required=true) String data
    		) throws IOException {
		
		 Article art = new Article();
		 if (artId.equals("")){
			 //标志值，为-1表示新增
			 art.setId(-1);
		 } else {
			 art.setId(Integer.parseInt(artId));
		 }
		 art.setTitle(title);
		 art.setClassId(classId);
		 HttpEntityJson entity = new HttpEntityJson();
		 if (service.addArticle(art,data)){
			 entity.setSuccess();
		 } else {
			 entity.setFail();
		 }
		 return new ResponseEntity<String>(entity.toJSONString(), HttpStatus.OK);
    }
	
	@RequestMapping(path="type", method=RequestMethod.POST)
    public HttpEntity<String> addType(
    		@RequestParam(value="pid", required=true) String pid,
    		@RequestParam(value="name", required=true) String name
            ) {
		HttpEntityJson entity = new HttpEntityJson();
		ArtClass cls = new ArtClass();
		cls.setPid(pid);
		cls.setName(name);
		if (service.addType(cls)){
			entity.setBusiData("id", cls.getId());
			entity.setSuccess();
		} else {
			entity.setFail();
		}
		return new ResponseEntity<String>(entity.toJSONString(), HttpStatus.OK);
    }
	
	@RequestMapping(value="addInfos", method=RequestMethod.POST)
    public HttpEntity<String> addL2Class(@RequestParam(value="name", required=true) String name) {
		L2Class l2 = new L2Class();
		HttpEntityJson entity = new HttpEntityJson();
		l2.setName(name);
		if (service.addL2(l2)){
			entity.setBusiData("id", l2.getId());
			entity.setSuccess();
		} else {
			entity.setFail();
		}
		return new ResponseEntity<String>(entity.toJSONString(), HttpStatus.OK);
    }

	@RequestMapping(path="typesOfL2", method=RequestMethod.GET)
    public HttpEntity<String> getAllType(
    		@RequestParam(value="id", required=true) String l2Id) {
		List<ArtClass> o = service.getTypeOfL2(l2Id);

		return new ResponseEntity<String>(JSONObject.toJSONString(o), HttpStatus.OK);
    }
}
