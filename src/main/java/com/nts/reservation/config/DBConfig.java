/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.nts.reservation.mapper.CategoryMapper;

/**
 * DBCP 설정 클래스
 * @author jinwoo.bae
 */
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class DBConfig implements TransactionManagementConfigurer {
	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;
	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.username}")
	private String id;
	@Value("${spring.datasource.password}")
	private String pw;

	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(id);
		dataSource.setPassword(pw);
		return dataSource;
	}

	@Bean
	public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource);
		return factoryBean;
	}

	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return transactionManger();
	}

	@Bean
	public PlatformTransactionManager transactionManger() {
		return new DataSourceTransactionManager(dataSource());
	}

	@Bean
	public MapperFactoryBean<CategoryMapper> categoryMapper(SqlSessionFactory sqlSessionFactory) {
		MapperFactoryBean<CategoryMapper> mapperFactoryBean = new MapperFactoryBean<CategoryMapper>();
		mapperFactoryBean.setMapperInterface(CategoryMapper.class);
		mapperFactoryBean.setSqlSessionFactory(sqlSessionFactory);
		return mapperFactoryBean;
	}

}