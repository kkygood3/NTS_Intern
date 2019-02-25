/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.aspect;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author 전연빈
 */
@Aspect
@Component
public class ErrorLogAspect {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@AfterThrowing(value = "execution (* *..*Controller.*(..))", throwing = "e")
	public void printErrorLog(Throwable e) {

		logger.info(ExceptionUtils.getStackTrace(e));
	}
}
