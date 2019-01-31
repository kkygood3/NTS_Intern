/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * @desc Layer Architecture 설정
 * @author 전연빈
 */
@Configuration
@ComponentScan(basePackages = {"com.nts.dao", "com.nts.service"})
@PropertySource(value = {"classpath:/application.properties"})
@Import({DataSourceConfig.class, TransactionManagementConfig.class, JdbcTemplateConfig.class})
public class ApplicationConfig {

}
