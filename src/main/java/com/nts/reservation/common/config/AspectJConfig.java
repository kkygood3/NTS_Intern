/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.nts.reservation.common.aspect.IsEmptyAnntationAspect;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AspectJConfig {

	@Bean
	public IsEmptyAnntationAspect isEmptyAnntationAspect() {
		return new IsEmptyAnntationAspect();
	}
}
