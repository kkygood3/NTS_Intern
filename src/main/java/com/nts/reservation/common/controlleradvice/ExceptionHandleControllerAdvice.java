/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.common.controlleradvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

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
import com.nts.reservation.common.exception.UnauthenticateException;
import com.nts.reservation.common.model.ExceptionResponse;

/**
 * service 내에서 발생하는 exception 핸들링, Exception 발생시 ExceptionResponse 객체 반환.
 * @author 임상현, life4lord93@nts-corp.com
 */
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

	@ExceptionHandler(UnauthenticateException.class)
	public ExceptionResponse handleUnauthenticateException(UnauthenticateException e) {
		return new ExceptionResponse(HttpStatus.UNAUTHORIZED, e.getMessage());
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ExceptionResponse handleConstraintViolationException(ConstraintViolationException e) {
		StringBuffer sb = new StringBuffer();
		for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations()) {
			sb.append("[");
			sb.append(constraintViolation.getInvalidValue());
			sb.append(", ");
			sb.append(constraintViolation.getMessageTemplate());
			sb.append("]");
			constraintViolation.getMessageTemplate();
		}
		return new ExceptionResponse(HttpStatus.BAD_REQUEST, sb.toString());
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
		return new ExceptionResponse(HttpStatus.BAD_REQUEST, sb.toString());
	}

	@ExceptionHandler(Exception.class)
	public ExceptionResponse handleRuntimeException(Exception e) {
		return new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, "server error");
	}

}
