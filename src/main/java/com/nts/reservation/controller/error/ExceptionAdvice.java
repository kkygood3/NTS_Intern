/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller.error;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.util.NestedServletException;

@ControllerAdvice
public class ExceptionAdvice {
	public static final String errorView = "error/invalidAccess";
	
	//404 오류
	@ExceptionHandler(NoHandlerFoundException.class)
	public String handlePageNotFound(NoHandlerFoundException ex, HttpServletRequest request) {
		request.setAttribute("cause", "알수없는 URL 요청");
		return errorView;
	}
	
	//500 오류	
	@ExceptionHandler(NestedServletException.class)
	public String handleNestedServlet(NestedServletException ex, HttpServletRequest request) {
		request.setAttribute("cause", "서버 오류");
		return errorView;
	}
	
	@ExceptionHandler(RuntimeException.class)
	public String handleRuntimeException(RuntimeException exception, HttpServletRequest request) throws UnsupportedEncodingException {
		if(exception.getCause() instanceof NumberFormatException) {
			//controller에서 받는 형 변환 할 수 없는 값이 들어올 때 처리. error 페이지를 출력 
			request.setAttribute("cause", "Parameter 오류");
		} else {
			//나머지 RuntimeExceptionHandler 처리
			String message = exception.getMessage().replaceAll("'", "\"");
			request.setAttribute("cause", message);
		}
		return errorView;
	}
}
