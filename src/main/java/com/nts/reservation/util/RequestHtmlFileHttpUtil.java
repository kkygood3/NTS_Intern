/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.util;

import java.net.ConnectException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * @author 육성렬
 */

@PropertySource("classpath:application.properties")
public class RequestHtmlFileHttpUtil {

	@Value("${render.server.url}")
	private String renderServerUrl;

	@Autowired
	private RestTemplate restTemplate;

	public String requestHtmlPost(String path, Object requestDto)
		throws HttpClientErrorException, ConnectException {

		String url = renderServerUrl + path;
		return restTemplate.postForObject(url, requestDto, String.class);
	}
}
