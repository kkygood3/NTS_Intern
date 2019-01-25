/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservationservice.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

/*
 * @author 육성렬
 */
@Configuration
@EnableTransactionManagement
public class DBConfig implements TransactionManagementConfigurer {

	@Autowired
	ResourceLoader resourceLoader;

	private String driverClassName;
	private String url;

	private String username;
	private String password;

	@Bean
	public DataSource dataSource() {

		Resource resource = resourceLoader.getResource("classpath:application.properties");
		try {
			InputStream inputStream = resource.getInputStream();
			Properties dbProperties = new Properties();
			dbProperties.load(inputStream);
			driverClassName = dbProperties.getProperty("spring.datasource.driver-class-name");
			url = dbProperties.getProperty("spring.datasource.url");
			username = dbProperties.getProperty("spring.datasource.username");
			password = dbProperties.getProperty("spring.datasource.password");
		} catch (IOException e) {
			e.printStackTrace();
		}

		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}

	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return transactionManger();
	}

	@Bean
	public PlatformTransactionManager transactionManger() {
		return new DataSourceTransactionManager(dataSource());
	}
}