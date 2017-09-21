package com.tianrun.artM.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tianrun.artM.pojo.ArtClass;
import com.tianrun.artM.pojo.Article;
import com.tianrun.artM.pojo.L2Class;
import com.tianrun.artM.service.ArtService;

@Controller
public class ArticleShowController {
	
	@Autowired
    private ArtService service;
	
	/**
	 * 二级菜单页面（二级表示微信企业号中的二级菜单）
	 * @param classId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="l2Page", method=RequestMethod.GET)
    public String level2(Model model) {
		List<L2Class> o = service.getL2Class();
		model.addAttribute("level2", o);
        return "level2";
    }
	
	@RequestMapping(path="mainPage", method=RequestMethod.GET)
    public String show(
    		@RequestParam(value="id", required=false) String pid,
    		Model model) {
			
//		List<ArtClass> o = service.getClassList();
		List<ArtClass> o = service.getTypeOfL2(pid);
		model.addAttribute("pid", pid);
		model.addAttribute("typeList", o);
        return "main";
    }
	
	@RequestMapping(path="editPage", method=RequestMethod.GET)
    public String updArticle(
    		@RequestParam(value="pid", required=true) String pid,
    		@RequestParam(value="id", required=false,defaultValue = "") String id,
    		@RequestParam(value="name", required=false,defaultValue = "") String name,
    		@RequestParam(value="artId", required=false,defaultValue = "") String articleId,
    		Model model) {
		List<ArtClass> o = service.getTypeOfL2(pid);
		
		model.addAttribute("typeList", o);
		model.addAttribute("typeId",id);
		model.addAttribute("typeName",name);
		if (!articleId.isEmpty()){
			Article art  = service.getArticleById(articleId);
			
			model.addAttribute("artId",articleId);
			model.addAttribute("title",art.getTitle());
			model.addAttribute("content",art.getContent());
		} else {
			model.addAttribute("artId","");
			model.addAttribute("title","");
			model.addAttribute("content","");
		}
        return "edit";
    }
	
}
