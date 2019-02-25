/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.controller.error;

import javax.naming.NoPermissionException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.nts.dto.error.ErrorMessage;

/**
 * @author 전연빈
 */
@ControllerAdvice(annotations = Controller.class)
public class ControllerExceptionAdvice {

	/**
	 * @desc 권한이 없는 사용자가 접근시 에러페이지로 이동
	 * @param e
	 * @return modelAndView
	 */
	@ExceptionHandler(NoPermissionException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ModelAndView noPermissionHandling(NoPermissionException e) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("error", new ErrorMessage(e.getMessage()));
		modelAndView.setViewName("error/401");
		
		return modelAndView;
	}
}
