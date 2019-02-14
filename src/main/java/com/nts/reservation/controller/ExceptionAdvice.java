package com.nts.reservation.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {
	/**
	 * controller에서 받는 int형 param에 형 변환 할 수 없는 값이 들어올 때 처리하는 역할. 
	 */
	@ExceptionHandler(NumberFormatException.class)
	public String NumberFormatException(HttpServletRequest req, Exception ex) {
		return "error";
	}
}
