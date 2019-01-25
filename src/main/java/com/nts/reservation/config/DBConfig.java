package com.nts.reservation.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
@EnableTransactionManagement
public class DBConfig implements TransactionManagementConfigurer {
	//	final private String driverClassName = "com.mysql.cj.jdbc.Driver";
	//	final private String url = "jdbc:mysql://localhost:3306/reservation?useUnicode=true&autoReconnect=true&useSSL=false&serverTimezone=UTC";
	//	final private String username = "root";
	//	final private String password = "root";

	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return transactionManger();
	}

	@Bean
	public PlatformTransactionManager transactionManger() {
		return new DataSourceTransactionManager(dataSource());
	}

	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		//		dataSource.setDriverClassName(driverClassName);
		//		dataSource.setUrl(url);
		//		dataSource.setUsername(username);
		//		dataSource.setPassword(password);
		return dataSource;
	}

}