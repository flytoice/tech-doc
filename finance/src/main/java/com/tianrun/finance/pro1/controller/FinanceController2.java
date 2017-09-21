package com.tianrun.finance.pro1.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tianrun.finance.pro1.listener.InitSpecialCompanyTaxInfo;
import com.tianrun.finance.pro1.pojo.SearchHis;
import com.tianrun.finance.pro1.pojo.TaxInfo;
import com.tianrun.finance.pro1.service.FinanceService;
import com.tianrun.finance.pro1.util.CRC16;

@Controller
public class FinanceController2 {
	
	@Autowired
    private FinanceService financeService;
	
	@RequestMapping(path="taxInfo", method=RequestMethod.GET)
    public String greeting(
    		HttpSession session,
    		@RequestParam(value="id", required=true) String id,
    		@RequestParam(value="name", required=true) String name,
    		Model model) {
		
		
		SearchHis h = new SearchHis();
		h.setCompanyId(Integer.parseInt(id));
		h.setCompanyName(name);
		h.setStaffId(Integer.parseInt(session.getAttribute("userId").toString()));
		TaxInfo o = financeService.getTaxInfo(h);
		if (o!=null){
			model.addAttribute("taxInfo", o);
			String split = "</>";
			StringBuilder sb = new StringBuilder();
			sb.append(o.getCompanyName());
			sb.append(split);
			sb.append(o.getTaxNumber());
			sb.append(split);
			sb.append(o.getAddress()+" ");
			sb.append(o.getPhone());
			sb.append(split);
			sb.append(o.getBank()+" ");
			sb.append(o.getCardNo());
			sb.append(split);
			
			try {
				String ctx2 = "$01"+Base64.encodeBase64String((sb.toString()+CRC16.getCrc16_IBM1(sb.toString())).getBytes("utf-8"))+"$";
				model.addAttribute("taxData", ctx2);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			 return "detail";
		} else {
			return "detail2";
		}
    }
	
	@RequestMapping(path="taxInfos", method=RequestMethod.GET)
    public String searchPage(HttpSession session,Model model) {
		SearchHis h = new SearchHis();
		h.setStaffId(Integer.parseInt(session.getAttribute("userId").toString()));
        return "index";
    }
	
	@RequestMapping(path="hot", method=RequestMethod.GET)
    public String searchHot(HttpSession session,Model model) {
		SearchHis h = new SearchHis();
		h.setStaffId(Integer.parseInt(session.getAttribute("userId").toString()));
		List<SearchHis> l = financeService.getSearchHis(h);
		l.add(0,(SearchHis)InitSpecialCompanyTaxInfo.get("trxn"));
		model.addAttribute("companys", l);
        return "hot";
    }
}
