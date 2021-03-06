package com.tianrun.finance.pro1.db;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

//@Configuration
public class MyBatisConfig implements TransactionManagementConfigurer{
	@Autowired 
    DataSource dataSource;
	
	//事务管理器
	@Bean
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return new DataSourceTransactionManager(dataSource);
	}
	
	//sqlSessionFactory
	@Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage("com.tianrun.finance.pro1.pojo");
        
      //分页插件 //4
//      PageHelper pageHelper = new PageHelper();
//      Properties properties = new Properties();
//      properties.setProperty("reasonable", "true");
//      properties.setProperty("supportMethodsArguments", "true");
//      properties.setProperty("returnPageInfo", "check");
//      properties.setProperty("params", "count=countSql");
//      pageHelper.setProperties(properties);
      //添加插件
//      bean.setPlugins(new Interceptor[]{pageHelper});
        
      //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
			bean.setMapperLocations(resolver.getResources("classpath:/mybatis/*Mapper.xml"));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
		
	}
	
	//SqlSessionTemplate
	@Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
