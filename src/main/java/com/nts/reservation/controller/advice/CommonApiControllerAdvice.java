/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.controller.advice;

import static com.nts.reservation.controller.advice.message.ErrorMessage.BAD_REQUEST_ERROR_MSG;
import static com.nts.reservation.controller.advice.message.ErrorMessage.COMMON_ERROR_MSG;
import static com.nts.reservation.controller.advice.message.ErrorMessage.INTERNAL_ERROR_MSG;
import static com.nts.reservation.controller.advice.message.ErrorMessage.NOT_FOUND_REQUEST;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nts.reservation.dto.ErrorMessageDto;
import com.nts.reservation.exception.CustomFileNotFoundException;
import com.nts.reservation.exception.InvalidParamException;

/**
 * @author 육성렬
 */
@RestControllerAdvice(annotations = RestController.class)
public class CommonApiControllerAdvice {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler({IOException.class, SQLException.class})
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessageDto handleIoException(Exception exception) {
		logger.error("error message, {}", exception);
		return new ErrorMessageDto(INTERNAL_ERROR_MSG);
	}

	@ExceptionHandler({BindException.class, InvalidParamException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessageDto handleBindException(Exception exception) {
		logger.error("error message, {}", exception);
		return new ErrorMessageDto(BAD_REQUEST_ERROR_MSG);
	}

	@ExceptionHandler({FileNotFoundException.class, CustomFileNotFoundException.class})
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorMessageDto handleFileNotFoundException(Exception exception) {
		logger.error("error message, {}", exception);
		return new ErrorMessageDto(NOT_FOUND_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessageDto handleCommonException(Exception exception) {

		logger.error("error message, {}", exception);
		return new ErrorMessageDto(COMMON_ERROR_MSG);
	}
}
