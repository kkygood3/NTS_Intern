/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class NotFoundDataException extends RuntimeException {

	public NotFoundDataException() {
		this("not found data !!");
	}

	public NotFoundDataException(Throwable e) {
		super(e);
	}

	public NotFoundDataException(String message) {
		super(message);
	}
}
