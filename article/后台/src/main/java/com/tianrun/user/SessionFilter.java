package com.tianrun.user;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        
        String url = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
//        if (url.startsWith("/") && url.length() > 1) {
//            url = url.substring(1);
//        }
//        httpRequest.getCookies()
        if (url.contains("login.html") 
        		|| url.contains("css/") 
        		|| url.contains("js/") 
        		|| url.contains("userLogin")) {
        	chain.doFilter(httpRequest, httpResponse);
        	return;
        }
        
        HttpSession session = httpRequest.getSession();
        if (session.getAttribute("userId") != null){
            // session存在
            chain.doFilter(httpRequest, httpResponse);
            return;
        } else {
            // session不存在 准备跳转失败
        	httpResponse.sendRedirect("login.html");
            return;
        }	
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
