/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.commons.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@ControllerAdvice
public class GlobalControllerAdvice {

	@ExceptionHandler(value = IllegalArgumentException.class)
	public ModelAndView handleIllegalArgumentException(IllegalArgumentException e) {
		e.printStackTrace();

		ModelAndView modelAndView = new ModelAndView("error/invalidAccess");
		modelAndView.setStatus(HttpStatus.BAD_REQUEST);
		return modelAndView;
	}

	@ExceptionHandler(value = DataAccessException.class)
	public ModelAndView handleDataAccessException(DataAccessException e) {
		e.printStackTrace();

		ModelAndView modelAndView = new ModelAndView("error/invalidAccess");
		return modelAndView;
	}
}