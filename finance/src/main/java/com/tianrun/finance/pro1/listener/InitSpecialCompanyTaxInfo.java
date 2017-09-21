package com.tianrun.finance.pro1.listener;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.tianrun.finance.pro1.pojo.SearchHis;
import com.tianrun.finance.pro1.service.FinanceService;

@Component
public final class InitSpecialCompanyTaxInfo implements ApplicationListener<ApplicationReadyEvent>{

	@Autowired
    private FinanceService financeService;
	
	private static HashMap<String,Object> map = new HashMap<String,Object>();
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent arg0) {
		//获取特定公司的开票信息
		SearchHis t = financeService.getTaxInfoByName("北京天润新能投资有限公司");
		map.put("trxn", t);
	}
	
	public static Object get(String key){
		return map.get(key);
	}
}
