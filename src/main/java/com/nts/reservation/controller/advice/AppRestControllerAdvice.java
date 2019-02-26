package com.nts.reservation.controller.advice;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 * 
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 *
 */

// This only states example to customize the handling methods for exceptions
@RestControllerAdvice(annotations = RestController.class)
public class AppRestControllerAdvice {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = {SQLException.class, RuntimeException.class})
	public ModelAndView handleBaseException(Exception e) {
		logger.error(e.getMessage() + "");
		return new ModelAndView("error/redirect").addObject("msg", e.getMessage()).addObject("url",
			"javascript:history.go(-1)");
	}
}