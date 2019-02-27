/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller.exceptionhandler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 예외처리를 위한 클래스
 * 예외발생시 error 페이지로 이동합니다.
 * @author jinwoo.bae
 */

@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * HTTP 404 ERROR
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleNotFoundPage(NoHandlerFoundException ex, HttpServletRequest req) {
		req.setAttribute("javax.servlet.error.status_code", 404);
		req.setAttribute("javax.servlet.error.message", "페이지를 찾을수 없습니다.");
		return "error";
	}

	/*
	 * @RequestParam 에러 처리
	 * MissingServletRequestParameterException - required=true인 파라미터가 없는 경우
	 * MethodArgumentTypeMismatchException - 파라미터의 자료형이 알맞지않는 경우
	 * NumberFormatException - 파라미터값인 String을 Integer로 파싱할때 값이 알맞지않는 경우
	 */
	@ExceptionHandler({MissingServletRequestParameterException.class, MethodArgumentTypeMismatchException.class,
		NumberFormatException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleParamException(HttpServletRequest req) {
		req.setAttribute("javax.servlet.error.status_code", 400);
		req.setAttribute("javax.servlet.error.message", "잘못된 입력값입니다.");
		return "error";
	}

	/**
	 *  그외 모든 익셉션 처리
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleOtherExceptions(Exception ex, HttpServletRequest req) {
		ex.printStackTrace();
		req.setAttribute("javax.servlet.error.status_code", 500);
		return "error";
	}
}
