/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

import com.nts.reservation.requester.HtmlFileRequester;

/**
 * @author 육성렬
 */
@Configuration
@ComponentScan(basePackages = {"com.nts.reservation.dao", "com.nts.reservation.service"})
@Import({DBConfig.class})
public class ApplicationConfig {

	@Bean
	public HtmlFileRequester requestHtmlFileHttp() {
		return new HtmlFileRequester();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
