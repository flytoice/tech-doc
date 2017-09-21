package com.tianrun.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

//@Component  
public class CorsFilter implements Filter {  
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException { 
    	
        HttpServletResponse response = (HttpServletResponse) res;  
//        response.setHeader("Access-Control-Allow-Origin", "http://weixin.tianrun.cn");  
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");  
        response.setHeader("Access-Control-Max-Age", "3600");  
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");  
        System.out.println("artManage*********************************过滤器被使用**************************");  
        chain.doFilter(req, res);  
    }  
    public void destroy() {}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}  
} 