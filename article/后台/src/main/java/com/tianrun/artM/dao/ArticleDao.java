package com.tianrun.artM.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tianrun.artM.pojo.ArtClass;
import com.tianrun.artM.pojo.Article;
import com.tianrun.artM.pojo.L2Class;


@Mapper
public interface ArticleDao {
	//分类列表
	public List<ArtClass> getClassList();
	
	//根据文章分类获取标题列表 
	public List<Article> getTitleByType(String classId);
	
	//增加文章分类
	public int addType(String name);
	
	//增加文章分类
	public int addType2(ArtClass cls);
	
	//根据uuid获取新增分类id
	public int getL3IdByUuid(String uuid);
	
	//清空uuid
	public int updL3(String uuid);
	
	//删除某文章
	public int delArticle(String id);
	
	//减少分类中文章数量
	public int minusCount(String id);
	
	//增加分类中文章数量
	public int addCount(String id);
	
	//新增文章
	public int addArticle(Article art);
	
	//通过uuid，获取id
	public int getIdByUuid(String uuid);
	
	//更新文章存储地址
	public int updAddr(Article art);
	
	//获取某文章信息
	public Article getArticle(String id);
	
	public int updArticle(Article art);
	
	//根据微信二级分类获取
	public List<L2Class> getl2();
	
	public int addL2(L2Class l2);
	
	public int updL2(int id);
	
	public int getL2IdByUuid(String uuid);
	
	public List<ArtClass> getClassListByPid(String pid);
	
	
}
