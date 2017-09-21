package com.tianrun.article.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tianrun.article.pojo.ArtClass;
import com.tianrun.article.pojo.Article;


@Mapper
public interface ArticleDao {
	//分类列表
	public List<ArtClass> getClassList(String pid);
	
	//根据文章分类获取标题列表 
	public List<Article> getTitleByType(String classId);
}
