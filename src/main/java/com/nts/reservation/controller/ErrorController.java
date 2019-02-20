/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.EmptyResultDataAccessException;
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
	 * Spring JdbcTemplate queryForObject 결과값이 empty이여도 정상적으로 리턴
	 */
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<Map<String, Object>> handleEmptyResult() {
		return new ResponseEntity<Map<String, Object>>(Collections.singletonMap("isEmpty", true), new HttpHeaders(), HttpStatus.OK);
	}

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
	public ResponseEntity<Map<String, Object>> handleRestApiValidationException() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isError", true);
		map.put("errorMsg", "wrong input");
		return new ResponseEntity<Map<String, Object>>(map, new HttpHeaders(), HttpStatus.BAD_REQUEST);
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
