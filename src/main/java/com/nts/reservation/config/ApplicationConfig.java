/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {"com.nts.reservation.controller", "com.nts.reservation.service", "com.nts.reservation.dao"})
@Import({DatabaseConfig.class, JdbcConfig.class, TransactionConfig.class})
@PropertySource(value = {"classpath:application.properties"})
public class ApplicationConfig {

}
