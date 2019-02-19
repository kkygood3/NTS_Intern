/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.commons.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalControllerAdvice {

	@ExceptionHandler(value = IllegalArgumentException.class)
	public ModelAndView  handleException416(IllegalArgumentException e) {
		e.printStackTrace();

		ModelAndView modelAndView = new ModelAndView("error/errorPage");
		modelAndView.addObject("errorMessage", e.getMessage());
		modelAndView.setStatus(HttpStatus.BAD_REQUEST);
		return modelAndView;
	}
}
