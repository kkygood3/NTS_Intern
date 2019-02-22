/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.common.controlleradvice;

import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalControllerAdvice {

	@ExceptionHandler(value = IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ModelAndView handleException400(IllegalArgumentException e) {
		// XXX logger level을 정하는 기준?
		ModelAndView modelAndView = new ModelAndView("common/error");
		modelAndView.addObject("errorMessage", e.getMessage());
		return modelAndView;
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	public ModelAndView handleError405(HttpRequestMethodNotSupportedException e) {
		ModelAndView modelAndView = new ModelAndView("common/error");
		modelAndView.addObject("errorMessage", "지원하지 않는 요청입니다.");
		return modelAndView;
	}
}
