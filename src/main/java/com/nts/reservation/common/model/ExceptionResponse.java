/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.common.model;

import org.springframework.http.HttpStatus;

public class ExceptionResponse extends Response {

	private String message;

	public ExceptionResponse(HttpStatus httpStatus) {
		super(httpStatus);
	}

	public ExceptionResponse(HttpStatus httpStatus, String message) {
		super(httpStatus);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
