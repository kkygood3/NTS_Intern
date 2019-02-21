/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller.exception;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * Rest Api 예외처리 클래스 
 * 예외발생시 json 데이터로 응답합니다.
 * @author jinwoo.bae
 */
@RestControllerAdvice(annotations = RestController.class)
public class GlobalApiExceptionHadler {
	@ExceptionHandler(NullPointerException.class)
	public Map<String, Object> handleNullException() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isEmpty", true);
		return Collections.singletonMap("isEmpty", true);
	}

	/*
	 * @RequestParam 에러 처리
	 */
	@ExceptionHandler({MissingServletRequestParameterException.class, MethodArgumentTypeMismatchException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Map<String, Object> handleApiParamException() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isError", true);
		map.put("errorMsg", "wrong input");
		return map;
	}

	/**
	 *  그외 모든 익셉션 처리
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, Object> handleOtherExceptions(Exception ex, HttpServletRequest req) {
		ex.printStackTrace();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isEmpty", true);
		map.put("isError", true);
		return map;
	}
}
