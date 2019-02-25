/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.common.model;

import org.springframework.http.HttpStatus;

public class Response {

	private HttpStatus httpStatus;
	private int httpStatusCode;

	public Response() {
		this.httpStatus = HttpStatus.OK;
		this.httpStatusCode = httpStatus.value();
	}

	public Response(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
		this.httpStatusCode = httpStatus.value();
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public int getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(int httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

}
