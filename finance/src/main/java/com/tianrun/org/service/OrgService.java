package com.tianrun.org.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrun.org.dao.OrgDao;
import com.tianrun.org.model.OrgPojo;

@Service
public class OrgService {
	
	@Autowired
	OrgDao dao;
	
	public List<OrgPojo> getAllOrg(){
		return dao.getOrg();
	}
	
	public List<OrgPojo> getAllOrgData(){
		return dao.getOrgData();
	}
}
