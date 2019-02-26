/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.common.exception;

public class ReservationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String responseMessage;

	public ReservationException() {
		super();
	}

	public ReservationException(Throwable e) {
		super(e);
	}

	public ReservationException(String message) {
		super(message);
		this.responseMessage = message;
	}

	public ReservationException(String message, Throwable e) {
		super(message + "; " + e, e);
		this.responseMessage = message;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

}
