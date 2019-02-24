/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.common.controlleradvice;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice(basePackages = {"com.nts.reservation"})
public class GlobalControllerAdvice {

	private static final String ERROR_PAGE = "common/error";

	@ExceptionHandler(IllegalArgumentException.class)
	public ModelAndView handleException400(IllegalArgumentException e) {
		ModelAndView mav = new ModelAndView(ERROR_PAGE);
		mav.addObject("errorMessage", e.getMessage());
		return mav;
	}

	@ExceptionHandler(HttpSessionRequiredException.class)
	public ModelAndView handleError403(HttpSessionRequiredException e) {
		ModelAndView mav = new ModelAndView(ERROR_PAGE);
		mav.addObject("errorMessage", "비정상적인 접근입니다.");
		return mav;
	}

	// XXX 처리
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ModelAndView handleError405(HttpRequestMethodNotSupportedException e) {
		ModelAndView mav = new ModelAndView(ERROR_PAGE);
		mav.addObject("errorMessage", "지원하지 않는 요청입니다.");
		return mav;
	}

	@ExceptionHandler(SQLException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView handleError500(SQLException e) {
		ModelAndView mav = new ModelAndView(ERROR_PAGE);
		mav.addObject("errorMessage", "해당 서비스에 문제가 있습니다. 관리자에게 문의하세요.");
		return mav;
	}
}
