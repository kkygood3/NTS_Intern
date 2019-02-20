package com.nts.reservation.controller.advice;

import static com.nts.reservation.controller.advice.message.ErrorMessage.COMMON_ERROR_MSG;
import static com.nts.reservation.controller.advice.message.ErrorMessage.INTERNAL_ERROR_MSG;
import static com.nts.reservation.controller.advice.message.ErrorMessage.UNAUTHORIZED_ERROR_MSG;
import static com.nts.reservation.controller.advice.message.ErrorMessage.PAGE_NOT_FOUND;
import java.net.ConnectException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

import com.nts.reservation.dto.ErrorMessageDto;
import com.nts.reservation.exception.PageNotFoundException;
import com.nts.reservation.exception.UnauthorizedRequestException;

@ControllerAdvice(annotations = Controller.class)
public class CommonPageControllerAdvice {
	
	@ExceptionHandler(Exception.class)
	public ErrorMessageDto handleCommonException(Exception exception) {
		
		return new ErrorMessageDto(COMMON_ERROR_MSG);
	}
	
	@ExceptionHandler(HttpClientErrorException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessageDto handleHttpClientErrorException(HttpClientErrorException exception) {

		//요기 로그 남기고.
		return new ErrorMessageDto(INTERNAL_ERROR_MSG);
	}
	
	@ExceptionHandler(ConnectException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessageDto handleConnectException(ConnectException exception) {

		//요기 로그 남기고.
		return new ErrorMessageDto(INTERNAL_ERROR_MSG);
	}
	
	@ExceptionHandler(UnauthorizedRequestException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ErrorMessageDto handleUnauthorizedRequestException(UnauthorizedRequestException exception) {

		//요기 로그 남기고.
		return new ErrorMessageDto(UNAUTHORIZED_ERROR_MSG);
	}
	
	@ExceptionHandler(PageNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorMessageDto handlePageNotFoundException(PageNotFoundException exception) {

		//요기 로그 남기고.
		return new ErrorMessageDto(PAGE_NOT_FOUND);
	}
}
