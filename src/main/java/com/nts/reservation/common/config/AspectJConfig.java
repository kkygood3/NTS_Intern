/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.common.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = {
	"com.nts.reservation"}, useDefaultFilters = false, includeFilters = @Filter(type = FilterType.ASPECTJ, pattern = "com.nts.reservation..aspect.*"))
@EnableAspectJAutoProxy
public class AspectJConfig {

}
