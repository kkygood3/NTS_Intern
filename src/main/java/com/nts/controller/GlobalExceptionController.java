/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import com.nts.exception.InvalidParameterException;;

@ControllerAdvice
public class GlobalExceptionController {

	@ExceptionHandler(InvalidParameterException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleError(InvalidParameterException invalidParameterException) {
		ModelAndView mav = new ModelAndView("errorpage");
		mav.addObject("parameter", invalidParameterException.getErrMsg());
		return "errorpage";
	}

	
}