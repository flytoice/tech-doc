package com.tianrun.artM.service;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.tianrun.artM.dao.ArticleDao;
import com.tianrun.artM.pojo.ArtClass;
import com.tianrun.artM.pojo.Article;
import com.tianrun.artM.pojo.L2Class;

@Service
public class ArtService {
	@Autowired
    public Environment env;
	
	@Autowired
	ArticleDao dao;
	
	public List<ArtClass> getClassList(){
		return dao.getClassList();
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
	
	//增加文章分类
	public boolean addType(String typeName){
		if (1==dao.addType(typeName)){
			return true;
		} else {
			return false;
		}
	}
	
	//增加文章分类
	public boolean addType(ArtClass cls){
		//uuid
		String uuid = UUID.randomUUID().toString();
		cls.setUuid(uuid);
		if (1==dao.addType2(cls)){
			int id = dao.getL3IdByUuid(uuid);
			cls.setId(id);
			dao.updL3(uuid);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 删除文章
	 * @param articleId
	 * @param typeId
	 * @return
	 */
	@Transactional
	public boolean delArticle(String articleId,String typeId){
		dao.delArticle(articleId);
		dao.minusCount(typeId);
		return true;
	}
	
	/**
	 * 新增文章
	 * @param art
	 * @return
	 * @throws IOException 
	 */
	@Transactional
	public boolean addArticle(Article art,String data) throws IOException{
		
		if (art.getId()==-1){
			//分类数量加1
			dao.addCount(art.getClassId());
			//uuid
			String uuid = UUID.randomUUID().toString();
			art.setUuid(uuid);
			//insert
			dao.addArticle(art);
			//根据uuid，获取Id，并更新addr
			art.setId(dao.getIdByUuid(uuid));
			art.generateAddr();
			dao.updAddr(art);
		} else {
			art.generateAddr();
			dao.updArticle(art);
		}
		
		try {
			String parent = env.getProperty("article-path");
			File target = new File(parent+art.getAddr());
			if (!target.getParentFile().exists()){
				target.getParentFile().mkdirs();
			}
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(target));
			byte[] uft8bom={(byte)0xef,(byte)0xbb,(byte)0xbf};
			out.write(uft8bom);
			out.write(data.getBytes("UTF-8"));
			out.flush();
			out.close();
			
		} catch (IOException e) {
			System.out.println("保存文章内容时出错："+env.getProperty("article-path")+art.getAddr());
			throw new RuntimeException(e);
		}
		return true;
	}
	
	public Article getArticleById(String id){
		Article art = dao.getArticle(id);
		String html = env.getProperty("article-path")+art.getAddr();
		File f = new File(html);
		if (f.exists()){
			try {
				StringBuilder content = new StringBuilder();
				BufferedReader reader = new BufferedReader(new InputStreamReader(  
		                new FileInputStream(f), "UTF-8"));  
		        String line = null;  
		        while ((line = reader.readLine()) != null) {  
		            content.append(line+ "\n");  
		        }  
		        reader.close();  
		        art.setContent(content.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return art;
	}
	
	//根据文章分类获取标题列表 
	public List<L2Class> getL2Class(){
		return dao.getl2();
	}
	
	//增加文章分类
	@Transactional
	public boolean addL2(L2Class l2){
		//uuid
		String uuid = UUID.randomUUID().toString();
		
		l2.setUuid(uuid);
		dao.addL2(l2);
		l2.setId(dao.getL2IdByUuid(uuid));
		dao.updL2(l2.getId());
		return true;
	}
	
	//根据二级菜单获取期子分类
	public List<ArtClass> getTypeOfL2(String pid){
		return dao.getClassListByPid(pid);
	}
}
