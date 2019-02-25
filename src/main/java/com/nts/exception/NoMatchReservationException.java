/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.exception;

public class NoMatchReservationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoMatchReservationException(String message) {
		super(message);
	}

}
