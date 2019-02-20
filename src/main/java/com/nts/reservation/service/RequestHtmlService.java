/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.service;

import java.net.ConnectException;

import org.springframework.web.client.HttpClientErrorException;

import com.nts.reservation.dto.request.MyReservationPageRequestDto;

/**
 * @author 육성렬
 */
public interface RequestHtmlService {
	public String requestMyReservationHtml(String path, MyReservationPageRequestDto requestDto)
		throws HttpClientErrorException, ConnectException;
}
