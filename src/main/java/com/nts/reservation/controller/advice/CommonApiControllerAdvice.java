/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.controller.advice;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nts.reservation.controller.advice.message.ErrorMessage;
import com.nts.reservation.dto.ErrorMessageDto;

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
}
