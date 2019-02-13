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
	public static final String DEFAULT_ERROR_VIEW = "/error";

	/*
	 * 400에러 처리
	 */
	@ExceptionHandler({MissingServletRequestParameterException.class, MethodArgumentTypeMismatchException.class,
		IllegalStateException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ModelAndView badRequest(MissingServletRequestParameterException msrpe,
		MethodArgumentTypeMismatchException matme, IllegalStateException ise) throws IOException {
		String message = null;
		if (msrpe != null) {
			message = msrpe.getMessage();
		} else if (matme != null) {
			message = matme.getMessage();
		} else if (ise != null) {
			message = ise.getMessage();
		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("errorInfo", new ErrorInfo(500, "Internal Server Error", message));
		mav.setViewName(DEFAULT_ERROR_VIEW);
		return mav;
	}

	/**
	 * 404에러 처리
	 * @throws IOException 
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ModelAndView notFound(NoHandlerFoundException nhfe, ServletException se) throws IOException {
		String message = null;
		if (nhfe != null) {
			message = nhfe.getMessage();
		} else if (se != null) {
			message = se.getMessage();
		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("errorInfo", new ErrorInfo(404, "Page Not Found", message));
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
		mav.addObject("errorInfo", new ErrorInfo(500, "Internal Server Error", bsge.getMessage()));
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
		mav.addObject("errorInfo", new ErrorInfo(0, "KNOWN", e.getMessage()));
		mav.setViewName(DEFAULT_ERROR_VIEW);
		return mav;
	}

	public class ErrorInfo {
		private String message;
		private String statusName;
		private int status;

		ErrorInfo(int status, String statusName, String message) {
			this.status = status;
			this.statusName = statusName;
			this.message = message;
		}

		public String getMessage() {
			return message;
		}

		public String getStatusName() {
			return statusName;
		}

		public int getStatus() {
			return status;
		}
	}
}
