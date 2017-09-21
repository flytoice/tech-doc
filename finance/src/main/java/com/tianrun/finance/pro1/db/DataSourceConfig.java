package com.tianrun.finance.pro1.db;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

//@Configuration
//@PropertySource("classpath:jdbc.properties")
public class DataSourceConfig {
	@Value("${jdbc.driver}")
	private String driver;
	
	@Value("${jdbc.url}")
	private String url;

	@Value("${jdbc.username}")
	private String username;
	
	@Value("${jdbc.password}")
	private String password;
	
	@Value("${jdbc.minIdle}")
	private int minIdle;
	
	@Value("${jdbc.maxIdle}")
	private int maxIdle;
	
	@Value("${jdbc.maxActive}")
	private int maxActive;
	
	@Value("${jdbc.initialSize}")
	private int initialSize;
	
	@Bean
    public DataSource dataSource(){ //3
        DataSource dataSource = new DataSource(); 
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxIdle(maxIdle);
        dataSource.setMaxActive(maxActive);
        dataSource.setInitialSize(initialSize);
        dataSource.setDefaultAutoCommit(false);
        dataSource.setTestWhileIdle(true);
        return dataSource;
    }
	
	
}
