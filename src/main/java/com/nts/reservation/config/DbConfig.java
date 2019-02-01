/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class DbConfig implements TransactionManagementConfigurer {
	private static final String DRIVER = "spring.datasource.driver-class-name";
	private static final String URL = "spring.datasource.url";
	private static final String USE_RNAME = "spring.datasource.username";
	private static final String PASSWORD = "spring.datasource.password";
	
	@Autowired
	Environment environment;

	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(environment.getProperty(DRIVER));
		dataSource.setUrl(environment.getProperty(URL));
		dataSource.setUsername(environment.getProperty(USE_RNAME));
		dataSource.setPassword(environment.getProperty(PASSWORD));
		return dataSource;
	}
	
	// 
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return transactionManger();
	}

	@Bean
	public PlatformTransactionManager transactionManger() {
		return new DataSourceTransactionManager(dataSource());
	}
}
