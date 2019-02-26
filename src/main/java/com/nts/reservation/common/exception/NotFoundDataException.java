/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.common.exception;

public class NotFoundDataException extends ReservationException {

	private static final long serialVersionUID = 1L;

	public NotFoundDataException() {
		this("not found data !!");
	}

	public NotFoundDataException(Throwable e) {
		super(e);
	}

	public NotFoundDataException(String message) {
		super(message);
	}

	public NotFoundDataException(String message, Throwable e) {
		super(message, e);
	}
}
