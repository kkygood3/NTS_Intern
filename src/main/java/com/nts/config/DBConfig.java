package com.nts.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.nts.constant.DBAccount;

@Configuration
@EnableTransactionManagement
public class DBConfig implements TransactionManagementConfigurer{
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(DBAccount.DRIVER_CLASS_NAME);
		dataSource.setUrl(DBAccount.URL);
		dataSource.setUsername(DBAccount.USERNAME);
		dataSource.setPassword(DBAccount.PASSWORD);
		return dataSource;
	}

	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return transactionManger();
	}

	@Bean
	public PlatformTransactionManager transactionManger() {
		return new DataSourceTransactionManager(dataSource());
	}
}
