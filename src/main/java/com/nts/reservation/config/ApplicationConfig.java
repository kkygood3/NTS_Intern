package com.nts.reservation.config;
/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 */

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

@Configuration
@ComponentScan(basePackages = {"com.nts.reservation"})
@Import({DBConfig.class})
public class ApplicationConfig {

}
