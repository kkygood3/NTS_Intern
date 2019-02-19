/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.common.controllerAdvice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalControllerAdvice {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@ExceptionHandler(value = IllegalArgumentException.class)
	public ModelAndView handleException400(IllegalArgumentException e) {
		logger.warn("!warn : {}", e.getMessage());
		e.printStackTrace();

		ModelAndView modelAndView = new ModelAndView("common/error");
		modelAndView.addObject("errorMessage", "BAD_REQUEST!! Please try agin...");
		modelAndView.setStatus(HttpStatus.BAD_REQUEST);
		return modelAndView;
	}
}
