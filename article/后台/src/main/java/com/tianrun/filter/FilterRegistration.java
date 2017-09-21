package com.tianrun.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
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
	
	/**
	 * 函数以characterEncodingFilter命名，
	 * System.out.println("ddddddd"),没有输出；
	 * 换个函数名，则输出，表示springboot已经将字符编码过滤器加入进来了。
	 * @return
	 */
//	@Bean
//	public FilterRegistrationBean characterEncodingFilter() {
//		FilterRegistrationBean registration = new FilterRegistrationBean();
//		
//		CharacterEncodingFilter f = new CharacterEncodingFilter();
//		f.setEncoding("UTF-8");
//		System.out.println("ddddddd");
//		registration.setFilter(f);
//		registration.addUrlPatterns("/*");
//		registration.setName("encodingFilter2");
//		return registration;
//	}
}
