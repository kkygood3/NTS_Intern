/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @desc Product Parameter start가 음수일때는 잘못된 요청에 대한 error response
 * @author 전연빈
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "잘못된 요청입니다.")
public class ProductParamException extends Exception {
	private static final long serialVersionUID = 1L;

	public ProductParamException(String message) {
		super(message);
	}
}
