package com.tianrun.finance.pro1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.tianrun.finance.pro1.pojo.TaxInfo;
import com.tianrun.finance.pro1.service.FinanceService;

@RestController
public class FinanceController {
	
	@Autowired
    private FinanceService financeService;
	
	/**
	 * 根据short name搜索公司
	 * @param name
	 * @return
	 */
	@RequestMapping(path="companyByName", method=RequestMethod.GET)
    public HttpEntity<String> getTaxInfos(
            @RequestParam(value = "name", required = true) String name
            ) {
		
		List<TaxInfo> o = financeService.getTaxInfos(name);
		
		return new ResponseEntity<String>(JSON.toJSONString(o), HttpStatus.OK);
    }
}
