package com.tianrun.org.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONArray;
import com.tianrun.org.model.OrgOption;
import com.tianrun.org.model.OrgPojo;
import com.tianrun.org.service.OrgService;


@Controller
public class OrgController {
	@Autowired
	OrgService service;
	
	@RequestMapping(path = "orgPage", method = RequestMethod.GET)
	public String getOrgPage(HttpSession session,Model model) {
		
		List<OrgPojo> l = service.getAllOrg();
		for(int i=0;i<l.size();i++){
			if (l.get(i).getParent().equals("0")){
				l.get(i).setParent("#");
				break;
			}
		}
		
		JSONArray jsonArray=new JSONArray();//1、创建JSONArray
        jsonArray.addAll(l);
        
		model.addAttribute("orgdata", jsonArray);
		
		return "org";
	}
	
	@RequestMapping(path = "orgData", method = RequestMethod.GET)
	public String getOrgData(HttpSession session,Model model) {
		List<OrgPojo> l = service.getAllOrgData();
		
		//ID:Object
		HashMap<String,OrgOption> _1st = new HashMap<String,OrgOption>();
		HashMap<String,OrgOption> _2nd = new HashMap<String,OrgOption>();
		HashMap<String,OrgOption> _3rd = new HashMap<String,OrgOption>();
		HashMap<String,OrgOption> _4th = new HashMap<String,OrgOption>();
		
		HashMap<String,HashMap<String,OrgOption>> map = new HashMap<String,HashMap<String,OrgOption>>(4);
		map.put("1", _1st);
		map.put("2", _2nd);
		map.put("3", _3rd);
		map.put("4", _4th);
		
		//下拉列表选项
		OrgOption expose;
		OrgOption op;
		
		//顶级组织
		op = new OrgOption();
		op.setName(l.get(0).getText());
		op.setValue(String.valueOf(l.get(0).getId()));
		map.get(l.get(0).getLevel()).put(String.valueOf(l.get(0).getId()), op);
		expose = op;
		
		OrgPojo dumy;
		for(int i=1;i<l.size();i++){
			dumy = l.get(i);
			
			op = new OrgOption();
			op.setName(dumy.getText());
			op.setValue(String.valueOf(dumy.getId()));
			map.get(dumy.getLevel()).put(String.valueOf(dumy.getId()), op);
			
			map.get(String.valueOf(Integer.valueOf(dumy.getLevel())-1)).get(dumy.getParent()).addSub(op);
		}
		
		JSONArray rtn = new JSONArray();
		rtn.add(expose);
		System.out.println(rtn.toJSONString());
		
		return "org";
	}
}
