package com.nts.reservation.controller;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;


@ControllerAdvice
public class ErrorController {
	public static final String DEFAULT_ERROR_VIEW = "error";
	/**
	 * 404에러 처리
	 * @throws IOException 
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ModelAndView notFound(NoHandlerFoundException nhfe, ServletException se) throws IOException {
		ModelAndView mav = new ModelAndView();

		if (nhfe != null) {
			mav.addObject("message", nhfe.getMessage());
		} else if (se != null) {
			mav.addObject("message", se.getMessage());
		}
		mav.addObject("status", "404");
		mav.addObject("statusName", "Page Not Found");
		mav.setViewName(DEFAULT_ERROR_VIEW);
		return mav;
	}

	/*
	 * 400에러 처리
	 */
	@ExceptionHandler({MissingServletRequestParameterException.class, MethodArgumentTypeMismatchException.class, IllegalStateException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ModelAndView badRequest(MissingServletRequestParameterException msrpe, MethodArgumentTypeMismatchException matme, IllegalStateException ise) throws IOException {
		ModelAndView mav = new ModelAndView();
		if (msrpe != null) {
			mav.addObject("message", msrpe.getMessage());
		} else if (matme != null) {
			mav.addObject("message", matme.getMessage());
		} else if (ise != null) {
			mav.addObject("message", ise.getMessage());
		}
		mav.addObject("status", "400");
		mav.addObject("statusName", "Bad Request");
		mav.setViewName(DEFAULT_ERROR_VIEW);
		return mav;
	}
	
	/*
	 * 500에러 처리
	 */
	@ExceptionHandler(BadSqlGrammarException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView intervalServerError(BadSqlGrammarException bsge) throws IOException {
		ModelAndView mav = new ModelAndView();
		mav.addObject("message", bsge.getMessage());
		mav.addObject("status", "500");
		mav.addObject("statusName", "Internal Server Error");
		mav.setViewName(DEFAULT_ERROR_VIEW);
		return mav;
	}
	
	/*
	 *  기타 에러 처리
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView unknown(Exception e) throws IOException {
		ModelAndView mav = new ModelAndView();
		mav.addObject("message", e.getMessage());
		mav.addObject("statusName", "KNOWN");
		mav.setViewName(DEFAULT_ERROR_VIEW);
		return mav;
	}
}
