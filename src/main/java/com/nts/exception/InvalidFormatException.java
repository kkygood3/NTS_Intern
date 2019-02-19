/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @desc displayInfoId가 없는 요청일 때
 * @author 전연빈
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "잘못된 형식입니다.")
public class InvalidFormatException extends Exception{
	private static final long serialVersionUID = 1L;

	public InvalidFormatException(String message) {
		super(message);
	}
}
