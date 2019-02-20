/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.controller.error;

import java.security.InvalidParameterException;

import javax.naming.NoPermissionException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nts.dto.error.ErrorMessage;
import com.nts.exception.DisplayInfoNullException;
import com.nts.exception.InvalidFormatException;
import com.nts.exception.NoMatchReservationException;

/**
 * @author 전연빈
 */
@RestControllerAdvice(annotations = RestController.class)
public class RestControllerExceptionAdvice {
	
	@ExceptionHandler(DisplayInfoNullException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorMessage displayNotFound(DisplayInfoNullException e) {
		e.printStackTrace();
		return new ErrorMessage(e.getMessage());
	}
	
	@ExceptionHandler(InvalidFormatException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorMessage invalidFormat(InvalidFormatException e) {
		e.printStackTrace();
		return new ErrorMessage(e.getMessage());
	}
	
	@ExceptionHandler(InvalidParameterException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorMessage invalidParameter(InvalidParameterException e) {
		e.printStackTrace();
		return new ErrorMessage(e.getMessage());
	}
	
	@ExceptionHandler(NoPermissionException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public ErrorMessage noPermission(NoPermissionException e) {
		e.printStackTrace();
		return new ErrorMessage(e.getMessage());
	}
	
	@ExceptionHandler(NoMatchReservationException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorMessage noMatchReservationInformation(NoMatchReservationException e) {
		e.printStackTrace();
		return new ErrorMessage(e.getMessage());
	}
}
