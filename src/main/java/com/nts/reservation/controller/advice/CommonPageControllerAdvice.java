package com.nts.reservation.controller.advice;

import static com.nts.reservation.controller.advice.message.ErrorMessage.COMMON_ERROR_MSG;
import static com.nts.reservation.controller.advice.message.ErrorMessage.INTERNAL_ERROR_MSG;
import static com.nts.reservation.controller.advice.message.ErrorMessage.NOT_FOUND_REQUEST;
import static com.nts.reservation.controller.advice.message.ErrorMessage.UNAUTHORIZED_ERROR_MSG;

import java.net.ConnectException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

import com.nts.reservation.dto.ErrorMessageDto;
import com.nts.reservation.exception.PageNotFoundException;
import com.nts.reservation.exception.UnauthorizedRequestException;
import com.nts.reservation.util.LogUtil;

@ControllerAdvice(annotations = Controller.class)
public class CommonPageControllerAdvice {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler(Exception.class)
	public @ResponseBody ErrorMessageDto handleCommonException(Exception exception) {
		logger.error("error msg : {} \n {}", exception.getMessage(), LogUtil.convertStackTraceToString(exception));
		return new ErrorMessageDto(COMMON_ERROR_MSG);
	}

	@ExceptionHandler(HttpClientErrorException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ErrorMessageDto handleHttpClientErrorException(HttpClientErrorException exception) {
		logger.error("error msg : {} \n {}", exception.getMessage(), LogUtil.convertStackTraceToString(exception));
		return new ErrorMessageDto(INTERNAL_ERROR_MSG);
	}

	@ExceptionHandler(ConnectException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ErrorMessageDto handleConnectException(ConnectException exception) {
		logger.error("error msg : {} \n {}", exception.getMessage(), LogUtil.convertStackTraceToString(exception));
		return new ErrorMessageDto(INTERNAL_ERROR_MSG);
	}

	@ExceptionHandler(UnauthorizedRequestException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public @ResponseBody ErrorMessageDto handleUnauthorizedRequestException(UnauthorizedRequestException exception) {
		logger.error("error msg : {} \n {}", exception.getMessage(), LogUtil.convertStackTraceToString(exception));
		return new ErrorMessageDto(UNAUTHORIZED_ERROR_MSG);
	}

	@ExceptionHandler(PageNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody ErrorMessageDto handlePageNotFoundException(PageNotFoundException exception) {
		logger.error("error msg : {} \n {}", exception.getMessage(), LogUtil.convertStackTraceToString(exception));
		return new ErrorMessageDto(NOT_FOUND_REQUEST);
	}
}
