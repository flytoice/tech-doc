package com.tianrun.org.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tianrun.org.model.OrgPojo;

@Mapper
public interface OrgDao {
	public List<OrgPojo> getOrg();
	
	public List<OrgPojo> getOrgData();
}