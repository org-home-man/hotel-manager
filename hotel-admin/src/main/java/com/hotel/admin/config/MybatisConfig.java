package com.hotel.admin.config;

import javax.sql.DataSource;

import com.github.pagehelper.PageInterceptor;
import com.hotel.core.page.PaginationInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Mybatis配置
 * @author Louis
 * @date Oct 29, 2018
 */
@Configuration
@MapperScan("com.hotel.*.mapper")	// 扫描DAO
public class MybatisConfig {
  @Autowired
  private DataSource dataSource;

  @Bean
  public SqlSessionFactory sqlSessionFactory() throws Exception {
    SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
    sessionFactory.setDataSource(dataSource);
    sessionFactory.setTypeAliasesPackage("com.hotel.*.model");	// 扫描Model
    
	PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
	sessionFactory.setMapperLocations(resolver.getResources("classpath*:**/mapper/xml/*.xml"));	// 扫描映射文件
    sessionFactory.setPlugins(new Interceptor[]{new PaginationInterceptor()});
    return sessionFactory.getObject();
  }
}