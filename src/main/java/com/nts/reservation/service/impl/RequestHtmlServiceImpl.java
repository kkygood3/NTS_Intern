/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.service.impl;

import java.net.ConnectException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.nts.reservation.requester.HtmlFileRequester;
import com.nts.reservation.service.RequestHtmlService;

/**
 * @author 육성렬
 */
@Service
public class RequestHtmlServiceImpl implements RequestHtmlService {

	private final HtmlFileRequester requestHtmlFileHttp;

	@Autowired
	public RequestHtmlServiceImpl(HtmlFileRequester requestHtmlFileHttp) {
		this.requestHtmlFileHttp = requestHtmlFileHttp;
	}

	@Override
	public <T> String requestToReactHtml(String path, T requestDto)
		throws HttpClientErrorException, ConnectException {

		return requestHtmlFileHttp.requestHtmlPost(path, requestDto);
	}

}
