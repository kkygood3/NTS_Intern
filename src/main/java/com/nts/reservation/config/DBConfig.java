package com.nts.reservation.config;

import static com.nts.reservation.config.ProjectInfo.BASE_PACKEGE;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

/**
 * DB 설정
 * @author 시윤
 *
 */
@Configuration
@EnableTransactionManagement
@PropertySource(value = {"classpath:application.properties"})
@MapperScan(BASE_PACKEGE + ".dao")
public class DBConfig implements TransactionManagementConfigurer {
	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;
	@Value("${spring.datasource.url}")
	private String dbUrl;
	@Value("${spring.datasource.username}")
	private String dbUsername;
	@Value("${spring.datasource.password}")
	private String dbPassword;

	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(dbUrl);
		dataSource.setUsername(dbUsername);
		dataSource.setPassword(dbPassword);
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
	
	@Bean
	public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource, ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
		sqlSessionFactoryBean.setTypeAliasesPackage(BASE_PACKEGE + ".dto");
		return sqlSessionFactoryBean;
	}
}