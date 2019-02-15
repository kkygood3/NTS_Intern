/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.error;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {
	/**
	 * controller에서 받는 int형 param에 형 변환 할 수 없는 값이 들어올 때 처리. error 페이지를 출력 
	 */
	@ExceptionHandler(NumberFormatException.class)
	public String NumberFormatException(HttpServletRequest req, Exception ex) {
		return "error";
	}
}
