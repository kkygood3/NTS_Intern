/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.exception;

/**
 * @author 육성렬
 */
public class UnauthorizedRequestException extends Exception {

	public UnauthorizedRequestException(String ipAddress, String requestUrl) {
		super("권한이 없는 요청입니다. [ipAddress]: " + ipAddress + " [requestUrl]: " + requestUrl);
	}
}
