package com.tianrun;

import com.alibaba.fastjson.JSONObject;

/**
 * restFul响应
 * @author flytoice
 *
 */
public class HttpEntityPojo extends JSONObject{
	/**
	 * 
	 */
	public HttpEntityPojo(){
		super();
		this.put("data", new JSONObject());
	}
	
	private static final long serialVersionUID = 1L;
	
	public static final String MSG_SUCCESS="SUCCESS";
	public static final String MSG_FAIL="FAIL";
	
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
		return this.getJSONObject("data");
	}
	public void setBusiData(String key,String value) {
		getBusiDataJson().put(key, value);
	}
	
}
