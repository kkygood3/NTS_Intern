/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.controller.advice;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nts.reservation.controller.advice.message.ErrorMessage;
import com.nts.reservation.dto.ErrorMessageDto;
import com.nts.reservation.exception.InValidPatternException;

/**
 * @author 육성렬
 */
@RestControllerAdvice(annotations = RestController.class)
public class CommonApiControllerAdvice {

	@ExceptionHandler(IOException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessageDto handleIoException(IOException exception) {

		//요기 로그 남기고.
		return new ErrorMessageDto(ErrorMessage.INTERNAL_ERROR_MSG);
	}
	
	@ExceptionHandler(SQLException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessageDto handleSqlException(SQLException exception) {

		//요기 로그 남기고.
		return new ErrorMessageDto(ErrorMessage.INTERNAL_ERROR_MSG);
	}
	
	@ExceptionHandler(BindException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessageDto handleBindException(BindException exception) {

		//요기 로그 남기고.
		return new ErrorMessageDto(ErrorMessage.BAD_REQUEST_ERROR_MSG);
	}
	
	@ExceptionHandler(InValidPatternException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessageDto handleInValidPatternException(InValidPatternException exception) {

		//요기 로그 남기고.
		return new ErrorMessageDto(ErrorMessage.BAD_REQUEST_ERROR_MSG);
	}
}
