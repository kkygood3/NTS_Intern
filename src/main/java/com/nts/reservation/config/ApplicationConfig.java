package com.nts.reservation.config;
/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 */

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

import com.nts.reservation.controller.ProductApiController;

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

@Configuration
//@ComponentScan(basePackages = {"com.nts.reservation.dao" , "com.nts.reservation.service" })
@ComponentScan(basePackages = {"com.nts.reservation"}, 
    excludeFilters = @Filter(
            type = FilterType.REGEX,
            pattern = "com\\.nts\\.reservation\\.controller\\*"))
@Import({DBConfig.class})
public class ApplicationConfig {

}
