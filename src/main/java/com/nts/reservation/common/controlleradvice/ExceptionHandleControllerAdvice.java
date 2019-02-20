/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.common.controlleradvice;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nts.reservation.common.exception.InternalServerErrorException;
import com.nts.reservation.common.exception.NotFoundDataException;
import com.nts.reservation.common.model.ExceptionResponse;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionHandleControllerAdvice {

	@ExceptionHandler(NotFoundDataException.class)
	public ExceptionResponse handleNotFoundDataException(NotFoundDataException e) {
		return new ExceptionResponse(HttpStatus.NOT_FOUND, e.getMessage());
	}

	@ExceptionHandler(InternalServerErrorException.class)
	public ExceptionResponse handleInternalServerErrorException(InternalServerErrorException e) {
		return new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ExceptionResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		StringBuffer sb = new StringBuffer();
		for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
			sb.append("[");
			sb.append(fieldError.getDefaultMessage());
			sb.append("]");
		}
		return new ExceptionResponse(HttpStatus.BAD_REQUEST, sb.toString());
	}

	@ExceptionHandler(BindException.class)
	public ExceptionResponse handleBindException(BindException e) {
		StringBuffer sb = new StringBuffer();
		for (FieldError fieldError : e.getFieldErrors()) {
			sb.append("[");
			sb.append(fieldError.getDefaultMessage());
			sb.append("]");
		}
		return new ExceptionResponse(HttpStatus.BAD_REQUEST);
	}
}
