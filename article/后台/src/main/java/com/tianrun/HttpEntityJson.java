package com.tianrun;

import com.alibaba.fastjson.JSONObject;

/**
 * restFul响应
 * @author flytoice
 *
 */
public class HttpEntityJson extends JSONObject{
	/**
	 * 
	 */
	public HttpEntityJson(){
		super();
		this.put("busiData", new JSONObject());
	}
	
	private static final long serialVersionUID = 1L;
	
	public static final String MSG_SUCCESS="SUCCESS";
	public static final String MSG_FAIL="FAIL";
	public static final String CODE_SUCCESS="1";
	public static final String CODE_FAIL="0";
	
	
	//业务代码 code
	//msg
	//业务数据 data
	public String getCode() {
		return this.getString("code");
	}
	public void setCode(String code) {
		this.put("code", code);
	}
	public String getMsg() {
		return this.getString("msg");
	}
	public void setMsg(String msg) {
		this.put("msg", msg);
	}
	public JSONObject getBusiDataJson() {
		return this.getJSONObject("busiData");
	}
	public void setBusiData(String key,Object value) {
		getBusiDataJson().put(key, value);
	}
	public void setSuccess(){
		setCode(CODE_SUCCESS);
		setMsg(MSG_SUCCESS);
	}
	public void setFail(){
		setCode(CODE_FAIL);
		setMsg(MSG_FAIL);
	}
	
}
