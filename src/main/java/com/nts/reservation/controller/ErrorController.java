package com.nts.reservation.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;


@ControllerAdvice
public class ErrorController {
	/**
	 * 404에러 처리
	 * @throws IOException 
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void notFound(NoHandlerFoundException nhfe, ServletException se) throws IOException {
	}

	/*
	 * 400에러 처리
	 */
	@ExceptionHandler({MissingServletRequestParameterException.class, MethodArgumentTypeMismatchException.class, IllegalStateException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public void badRequest(MissingServletRequestParameterException msrpe, MethodArgumentTypeMismatchException matme, IllegalStateException ise) throws IOException {
		System.out.println(ise);
	}
	
	/*
	 * 500에러 처리
	 */
	@ExceptionHandler(BadSqlGrammarException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public void intervalServerError(BadSqlGrammarException bsge) throws IOException {
		System.out.println(bsge);
	}
}
