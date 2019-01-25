/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

/**
 * @author 전연빈
 */
public class TransactionManagementConfig implements TransactionManagementConfigurer {

	@Autowired
	private DataSource dataSource;
	
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return this.transactionManager();
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource);
	}
}
