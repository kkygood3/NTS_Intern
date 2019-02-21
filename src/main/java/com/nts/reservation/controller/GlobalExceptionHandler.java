/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 예외처리를 위한 클래스
 * 예외발생시 error 페이지로 이동합니다.
 * @author jinwoo.bae
 */

@ControllerAdvice(annotations = Controller.class)
public class GlobalExceptionHandler {
	/**
	 * HTTP 404 ERROR
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String errorPage(NoHandlerFoundException ex, HttpServletRequest req) {
		req.setAttribute("javax.servlet.error.status_code", 404);
		return "error";
	}

	/*
	 * @RequestParam 에러 처리
	 */
	@ExceptionHandler({MissingServletRequestParameterException.class, MethodArgumentTypeMismatchException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public String handleParamException(HttpServletRequest req) {
		req.setAttribute("javax.servlet.error.status_code", 404);
		req.setAttribute("javax.servlet.error.message", "잘못된 입력갑입니다.");
		return "error";
	}

	/**
	 *  그외 모든 익셉션 처리
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String errorPage(Exception ex, HttpServletRequest req) {
		ex.printStackTrace();
		req.setAttribute("javax.servlet.error.status_code", 500);
		return "error";
	}
}
