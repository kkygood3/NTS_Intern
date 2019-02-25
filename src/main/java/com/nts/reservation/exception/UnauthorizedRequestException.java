/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.exception;

/**
 * @author 육성렬
 */
public class UnauthorizedRequestException extends Exception {

	private static final long serialVersionUID = -7310691220964309221L;

	public UnauthorizedRequestException(String ipAddress, String requestUrl) {
		super("권한이 없는 요청입니다. [ipAddress]: " + ipAddress + " [requestUrl]: " + requestUrl);
	}
}
