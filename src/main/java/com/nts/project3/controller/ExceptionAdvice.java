package com.nts.project3.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionAdvice {
	@ExceptionHandler(NumberFormatException.class)
	public String NumberFormatException(HttpServletRequest req, Exception ex) {
		return "error/invalidAccess";
	}
}
