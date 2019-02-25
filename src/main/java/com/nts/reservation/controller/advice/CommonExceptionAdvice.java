/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller.advice;

import java.net.ConnectException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.ModelAndView;

import com.nts.reservation.exception.ResourceNotFoundException;

/**
* @author  : 이승수
*/
@ControllerAdvice(annotations = Controller.class)
public class CommonExceptionAdvice {
	private static final Logger logger = LoggerFactory.getLogger(CommonExceptionAdvice.class);

	public ModelAndView showErrorPage(Exception e, HttpServletRequest request, String msg) {
		logger.error(e.toString());

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/error");
		mav.addObject("url", request.getRequestURL());
		mav.addObject("exception", e);
		mav.addObject("errorMsg", msg);
		return mav;
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleCommonException(Exception exception, HttpServletRequest request) {
		return showErrorPage(exception, request, "오류가 발생했습니다.");
	}

	@ExceptionHandler({BadSqlGrammarException.class, DataAccessException.class})
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView handleDatabaseException(Exception exception, HttpServletRequest request) {
		return showErrorPage(exception, request, "서버 내부 오류가 발생했습니다.");
	}

	@ExceptionHandler(HttpClientErrorException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView handleHttpClientErrorException(HttpClientErrorException exception, HttpServletRequest request) {
		return showErrorPage(exception, request, "서버 내부 오류가 발생했습니다.");
	}

	@ExceptionHandler(ConnectException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView handleConnectException(ConnectException exception, HttpServletRequest request) {
		return showErrorPage(exception, request, "서버 내부 오류가 발생했습니다.");
	}

	@ExceptionHandler(HttpServerErrorException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView handleHttpServerErrorException(HttpServerErrorException exception, HttpServletRequest request) {
		return showErrorPage(exception, request, "서버 내부 오류가 발생했습니다.");
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ModelAndView handlePageNotFoundException(ResourceNotFoundException exception, HttpServletRequest request) {
		return showErrorPage(exception, request, "요청한 페이지를 찾을 수 없습니다.");
	}
}
