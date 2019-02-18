/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.service.impl;

import java.net.ConnectException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.nts.reservation.dto.request.MyReservationPageRequestDto;
import com.nts.reservation.service.RequestHtmlService;
import com.nts.reservation.util.RequestHtmlFileHttpUtil;

/**
 * @author 육성렬
 */
@Service
public class RequestHtmlServiceImpl implements RequestHtmlService {

	@Autowired
	RequestHtmlFileHttpUtil requestHtmlFileHttp;

	@Override
	public String requestMyReservationHtml(String path, MyReservationPageRequestDto requestDto)
		throws HttpClientErrorException, ConnectException {

		return requestHtmlFileHttp.requestHtmlPost(path, requestDto);
	}

}
