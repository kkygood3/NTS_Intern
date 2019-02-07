package com.nts.reservation.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {
	@ExceptionHandler(NumberFormatException.class)
	public String NumberFormatException(HttpServletRequest req, Exception ex) {
		return "error";
	}
}
