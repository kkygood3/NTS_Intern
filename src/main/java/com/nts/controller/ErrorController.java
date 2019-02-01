/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.controller;

import javax.servlet.ServletException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 예외처리를 위한 컨트롤러 클래스
 * @author jinwoo.bae
 */

@ControllerAdvice
public class ErrorController {
	/**
	 * HTTP 404 ERROR
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String errorPage(NoHandlerFoundException ex, ServletException sex) {
		return "error";
	}

	/*
	 * @RequestParam 에러 처리
	 */
	@ResponseBody
	@ExceptionHandler({MissingServletRequestParameterException.class, MethodArgumentTypeMismatchException.class})
	public ResponseEntity<String> handleRestApiValidationException() {
		System.out.println("1");
		return new ResponseEntity<String>("wrong input.", new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
}
