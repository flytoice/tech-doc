package com.tianrun.finance.pro1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrun.finance.pro1.dao.FinanceDao;
import com.tianrun.finance.pro1.pojo.SearchHis;
import com.tianrun.finance.pro1.pojo.TaxInfo;

@Service
public class FinanceService {
	
	@Autowired
    private FinanceDao financeDao;
	
    public List<TaxInfo> getTaxInfos(String name){
    	return financeDao.getTaxInfos(name);
    }
    
    @Transactional
    public TaxInfo getTaxInfo(SearchHis his){
    	if (!his.getCompanyName().equals("北京天润新能投资有限公司")){
    		int r = financeDao.updateHis(his);
        	if (0==r){
        		financeDao.insertHis(his);
        	}
    	}
    	return financeDao.getTaxInfo(his.getCompanyId());
    }
    
    public List<SearchHis> getSearchHis(SearchHis his){
    	return financeDao.selectHis(his.getStaffId());
    }
    
    public SearchHis getTaxInfoByName(String name){
    	return financeDao.getTaxInfoByName(name);
    }
}
