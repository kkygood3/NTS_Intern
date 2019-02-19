/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "해당 Data가 없습니다")
public class NotFoundDataException extends RuntimeException {

	public NotFoundDataException(Throwable e) {
		super(e);
	}

	public NotFoundDataException(String message) {
		super(message);
	}
}
