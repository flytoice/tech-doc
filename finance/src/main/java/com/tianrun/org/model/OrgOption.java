package com.tianrun.org.model;

import java.util.ArrayList;

/**
 * 用于页面下拉列表
 * @author flytoice
 *
 */
public class OrgOption {
	private String name;
	private String value;
	private ArrayList<OrgOption> sub = new ArrayList<OrgOption>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public ArrayList<OrgOption> getSub() {
		return sub;
	}
	public void setSub(ArrayList<OrgOption> sub) {
		this.sub = sub;
	}
	public void addSub(OrgOption o){
		sub.add(o);
	}
	
}
