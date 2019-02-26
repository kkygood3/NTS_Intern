/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.common.exception;

public class ReservationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ReservationException() {
		super();
	}

	public ReservationException(Throwable e) {
		super(e);
	}

	public ReservationException(String message) {
		super(message);
	}

	public ReservationException(String message, Throwable e) {
		super(message, e);
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(super.toString() + "; ");

		if (getCause() != null) {
			sb.append("nested exception is " + getCause());
		}

		return sb.toString();
	}
}
