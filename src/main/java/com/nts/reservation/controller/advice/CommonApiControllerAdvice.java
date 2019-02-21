/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.controller.advice;

import static com.nts.reservation.controller.advice.message.ErrorMessage.BAD_REQUEST_ERROR_MSG;
import static com.nts.reservation.controller.advice.message.ErrorMessage.COMMON_ERROR_MSG;
import static com.nts.reservation.controller.advice.message.ErrorMessage.INTERNAL_ERROR_MSG;

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
import com.nts.reservation.exception.InvalidParamException;
import com.nts.reservation.util.LogUtil;

/**
 * @author 육성렬
 */
@RestControllerAdvice(annotations = RestController.class)
public class CommonApiControllerAdvice {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler(IOException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessageDto handleIoException(IOException exception) {
		logger.error("error msg : {} \n {}", exception.getMessage(), LogUtil.convertStackTraceToString(exception));
		return new ErrorMessageDto(INTERNAL_ERROR_MSG);
	}

	@ExceptionHandler(SQLException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessageDto handleSqlException(SQLException exception) {
		logger.error("error msg : {} \n {}", exception.getMessage(), LogUtil.convertStackTraceToString(exception));
		return new ErrorMessageDto(INTERNAL_ERROR_MSG);
	}

	@ExceptionHandler(BindException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessageDto handleBindException(BindException exception) {
		logger.error("error msg : {} \n {}", exception.getMessage(), LogUtil.convertStackTraceToString(exception));
		return new ErrorMessageDto(BAD_REQUEST_ERROR_MSG);
	}

	@ExceptionHandler(InvalidParamException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessageDto handleInvalidParamException(InvalidParamException exception) {
		logger.error("error msg : {} \n {}", exception.getMessage(), LogUtil.convertStackTraceToString(exception));
		return new ErrorMessageDto(BAD_REQUEST_ERROR_MSG);
	}

	@ExceptionHandler(Exception.class)
	public ErrorMessageDto handleCommonException(Exception exception) {
		System.out.println("common");
		logger.error("error msg : {} \n {}", exception.getMessage(), LogUtil.convertStackTraceToString(exception));
		return new ErrorMessageDto(COMMON_ERROR_MSG);
	}
}
