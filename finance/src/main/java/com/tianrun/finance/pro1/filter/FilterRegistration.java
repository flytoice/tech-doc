package com.tianrun.finance.pro1.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tianrun.finance.pro1.user.SessionFilter;

//@Configuration
public class FilterRegistration {

	/**
	 * 配置过滤器
	 * 
	 * @return
	 */
	@Bean
	public FilterRegistrationBean someFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new SessionFilter());
		registration.addUrlPatterns("/*");

		registration.setName("sessionFilter");
		return registration;
	}
}
