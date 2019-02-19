package com.nts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @desc displayInfoId가 없는 요청일 때
 * @author 전연빈
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "없는 displayInfo 입니다.")
public class DisplayInfoNullException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public DisplayInfoNullException(String message) {
		super(message);
	}
}
