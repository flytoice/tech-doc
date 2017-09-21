package com.tianrun.finance.pro1.user;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.tianrun.finance.pro1.pojo.UserInfo;
import com.tianrun.finance.pro1.user.service.UserService;


@WebFilter(urlPatterns={"/*"})
public class SessionFilter implements Filter {

	@Autowired
	UserService userService;
	
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        httpRequest.getCookies();
        String url = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
//        if (url.startsWith("/") && url.length() > 1) {
//            url = url.substring(1);
//        }
        
        if (url.contains(".html")
        		|| url.contains("orgPage")
        		|| url.contains("orgData")
        		|| url.contains("css/") 
        		|| url.contains("js/") 
        		|| url.contains("fonts/")
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
        	//自动登录
        	
        	if (httpRequest.getCookies()!=null){
	        	UserInfo user = new UserInfo();
	        	for (Cookie c : httpRequest.getCookies()) {
					if (c.getName().equals("wx_trxn_userName")){
						user.setPhone(c.getValue());
					}else if (c.getName().equals("wx_trxn_password")){
						user.setPwd(c.getValue());
					}
				}
	        	if (user.getPhone()!=null){
	        		String id = userService.checkUser(user);
	        		if (id != null){
	        			session.setAttribute("userId", id);
	        			session.setAttribute("phone", user.getPhone());
	        			chain.doFilter(httpRequest, httpResponse);
	                    return;
	        		}
	        	}
        	}
        	
        	httpResponse.sendRedirect("login.html");
            return;
        }	
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("filter init");
	}

}
