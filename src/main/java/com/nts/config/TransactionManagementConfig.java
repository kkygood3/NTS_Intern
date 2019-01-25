/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

public class TransactionManagementConfig implements TransactionManagementConfigurer {

	@Autowired
	private static DataSource dataSource;
	
	private static final PlatformTransactionManager TRANSACTION_MANAGER = new DataSourceTransactionManager(dataSource);

	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return this.TRANSACTION_MANAGER;
	}
}
