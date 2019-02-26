/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.nts.reservation.dto.response.ErrorResponseDto;
import com.nts.reservation.exception.InValidationException;

/**
 * Rest API용 예외처리 클래스 
 * 예외발생시 json 데이터로 응답합니다.
 * @author jinwoo.bae
 */
@RestControllerAdvice(annotations = RestController.class)
public class GlobalApiExceptionHadler {
	/**
	 *  validation 익셉션 처리
	 */
	@ExceptionHandler(InValidationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponseDto handleApiInValidationException(InValidationException ex) {
		return new ErrorResponseDto(true, ex.getMessage());
	}

	/**
	 * @RequestParam 에러 처리
	 * MissingServletRequestParameterException - required=true인 파라미터가 없는 경우
	 * MethodArgumentTypeMismatchException - 파라미터의 자료형이 알맞지않는 경우
	 * NumberFormatException - 파라미터값인 String을 Integer로 파싱할때 값이 알맞지않는 경우
	 */
	@ExceptionHandler({MissingServletRequestParameterException.class, MethodArgumentTypeMismatchException.class,
		NumberFormatException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponseDto handleApiParamException() {
		return new ErrorResponseDto(true, "wrong input");
	}

	/**
	 *  그외 모든 익셉션 처리
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponseDto handleApiOtherExceptions(Exception ex) {
		return new ErrorResponseDto(true, ex.getMessage());
	}
}
