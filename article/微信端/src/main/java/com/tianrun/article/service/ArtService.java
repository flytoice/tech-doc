package com.tianrun.article.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.tianrun.article.dao.ArticleDao;
import com.tianrun.article.pojo.ArtClass;
import com.tianrun.article.pojo.Article;

@Service
public class ArtService {
	@Autowired
	ArticleDao dao;
	
	public List<ArtClass> getClassList(String pid){
		return dao.getClassList(pid);
	}
	
	//根据文章分类获取标题列表 
	public List<Article> getTitleByType(String classId){
		return dao.getTitleByType(classId);
	}
	
	//根据文章分类、分页获取标题列表 
	public List<Article> getTitleByTypeAndPage(String classId,int pageNumber,int pageSize){
        PageHelper.startPage(pageNumber, pageSize);
        return dao.getTitleByType(classId);
	}
	
}
