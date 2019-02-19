/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.common.exception;

public class InternalServerErrorException extends RuntimeException {

	public InternalServerErrorException() {
		this("internal server error!!");
	}

	public InternalServerErrorException(Throwable e) {
		super(e);
	}

	public InternalServerErrorException(String message) {
		super(message);
	}
}
