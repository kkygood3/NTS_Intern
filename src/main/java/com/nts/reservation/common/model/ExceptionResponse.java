/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.common.model;

import org.springframework.http.HttpStatus;

public class ExceptionResponse {
	private HttpStatus httpStatus;
	private int httpStatusCode;
	private String message;

	public ExceptionResponse(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
		this.httpStatusCode = httpStatus.value();
	}

	public ExceptionResponse(HttpStatus httpStatus, String message) {
		this.httpStatus = httpStatus;
		this.httpStatusCode = httpStatus.value();
		this.message = message;
	}

	@Override
	public String toString() {
		return "ExceptionResponse [httpStatus=" + httpStatus + ", httpStatusCode=" + httpStatusCode + ", message="
			+ message + "]";
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
