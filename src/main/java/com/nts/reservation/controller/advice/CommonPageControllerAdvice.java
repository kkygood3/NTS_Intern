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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.ModelAndView;

import com.nts.reservation.dto.ErrorMessageDto;
import com.nts.reservation.exception.PageNotFoundException;
import com.nts.reservation.exception.UnauthorizedRequestException;

@ControllerAdvice(annotations = Controller.class)
public class CommonPageControllerAdvice {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ModelAndView getErrorPage(ErrorMessageDto errorDto) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("errorDto", errorDto);
		modelAndView.setViewName("errorPage");
		return modelAndView;
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView handleCommonException(Exception exception) {
		logger.error("error message, {}", exception);
		return getErrorPage(new ErrorMessageDto(COMMON_ERROR_MSG));
	}

	@ExceptionHandler({HttpClientErrorException.class, ConnectException.class, HttpServerErrorException.class})
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView handleHttpClientErrorException(Exception exception) {
		logger.error("error message, {}", exception);
		return getErrorPage(new ErrorMessageDto(INTERNAL_ERROR_MSG));
	}

	@ExceptionHandler(UnauthorizedRequestException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ModelAndView handleUnauthorizedRequestException(UnauthorizedRequestException exception) {
		logger.error("error message, {}", exception);
		return getErrorPage(new ErrorMessageDto(UNAUTHORIZED_ERROR_MSG));
	}

	@ExceptionHandler(PageNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ModelAndView handlePageNotFoundException(PageNotFoundException exception) {
		logger.error("error message, {}", exception);
		return getErrorPage(new ErrorMessageDto(NOT_FOUND_REQUEST));
	}
}
