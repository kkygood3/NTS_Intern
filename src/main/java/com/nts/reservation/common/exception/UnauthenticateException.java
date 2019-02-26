/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.common.exception;

public class UnauthenticateException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnauthenticateException() {
		this("unauthenticate !!");
	}

	public UnauthenticateException(Throwable e) {
		super(e);
	}

	public UnauthenticateException(String message) {
		super(message);
	}

	public UnauthenticateException(String message, Throwable e) {
		super(message, e);
	}
}
