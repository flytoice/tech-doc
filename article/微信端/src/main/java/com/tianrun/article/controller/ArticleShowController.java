package com.tianrun.article.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.tianrun.article.pojo.ArtClass;
import com.tianrun.article.pojo.Article;
import com.tianrun.article.service.ArtService;

@Controller
public class ArticleShowController {

	@Autowired
	public Environment env;

	@Autowired
	private ArtService service;

	@RequestMapping(path = "artCls", method = RequestMethod.GET)
	public String getTypes(
			@RequestParam(value = "pid", required = true) String pid,
			Model model) {
		List<ArtClass> o = service.getClassList(pid);
		model.addAttribute("types", o);
		return "clsList";
	}

	// @RequestMapping(path="titles", method=RequestMethod.GET)
	// public String getTitles(
	// @RequestParam(value="id", required=true) String classId,
	// Model model) {
	//
	// List<Article> o = service.getTitleByType(classId);
	// model.addAttribute("lists", o);
	// return "titleList";
	// }

	
	//第一页
	@RequestMapping(path = "titles", method = RequestMethod.GET)
	public String mobileShow(@RequestParam(value = "id", required = true) String classId, Model model) {
		int pageSize = Integer.parseInt(env.getProperty("spring.pagesize"));
		List<Article> o = service.getTitleByTypeAndPage(classId, 1, pageSize);
		PageInfo<Article> ctx = new PageInfo<Article>(o);
		
		model.addAttribute("lists", o.toArray());
		model.addAttribute("typeId",classId);
		model.addAttribute("pageNumber",ctx.getPageNum());
		model.addAttribute("pages",ctx.getPages());
		return "list";
	}

	//下一页
	@RequestMapping(path = "titlesByPage", method = RequestMethod.GET)
	public HttpEntity<String> getAll(@RequestParam(value = "id", required = true) String classId,
			@RequestParam(value = "pageNum", required = true, defaultValue = "1") int pageNumber,
			Model model) {
		
		int pageSize = Integer.parseInt(env.getProperty("spring.pagesize"));
		List<Article> o = service.getTitleByTypeAndPage(classId, pageNumber, pageSize);
		
		return new ResponseEntity<String>(JSONObject.toJSONString(o.toArray()), HttpStatus.OK);
	}
}
