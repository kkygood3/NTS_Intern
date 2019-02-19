/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.nts.exception.EmptyResultException;
import com.nts.exception.InvalidParameterException;


/**
 * @description : 모든 Controller에서 발생한 Custom Exception 처리
 * @package : com.nts.controller
 * @filename : GlobalExceptionController.java
 * @author : 최석현
 * @method : ModelAndView handleInvalidParameterException(InvalidParameterException invalidParameterException)
 * @method : ModelAndView handleEmptyResultException(EmptyResultException emptyResultException) 
 */
@ControllerAdvice
public class GlobalExceptionController {

	
	@ExceptionHandler(InvalidParameterException.class)
	public ModelAndView handleInvalidParameterException(InvalidParameterException invalidParameterException) {
		
		ModelAndView mav = new ModelAndView("redirect:/error/invalid-parameter-error");
		mav.addObject(invalidParameterException.getParamName(), invalidParameterException.getParamValue());
		
		return mav;
	}
	
	@ExceptionHandler(EmptyResultException.class)
	public ModelAndView handleEmptyResultException(EmptyResultException emptyResultException) {
		
		ModelAndView mav = new ModelAndView("redirect:/error/empty-resultset-error");
		mav.addObject(emptyResultException.getParamName(), emptyResultException.getParamValue());
		
		return mav;
	}
}