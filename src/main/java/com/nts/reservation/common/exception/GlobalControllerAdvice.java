/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = IllegalAccessError.class)
	public ModelAndView handleException500(IllegalAccessError e) {
		ModelAndView mav = new ModelAndView("common/error");
		mav.addObject("errorMessage", e.getMessage());
		return mav;
	}
}
