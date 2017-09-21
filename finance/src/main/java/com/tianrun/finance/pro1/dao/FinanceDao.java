package com.tianrun.finance.pro1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tianrun.finance.pro1.pojo.SearchHis;
import com.tianrun.finance.pro1.pojo.TaxInfo;

@Mapper
public interface FinanceDao {
	
	/**
	 * 模糊搜索匹配的公司税务信息
	 * @param name 公司名
	 * @return 返回列表
	 */
	public List<TaxInfo> getTaxInfos(String name);
	
	public TaxInfo getTaxInfo(int id);
	
	public List<SearchHis> selectHis(int staffId);
	
	public int insertHis(SearchHis his);

	public int updateHis(SearchHis his);
	
	//按照名字完全匹配搜索
	public SearchHis getTaxInfoByName (String name);
}
