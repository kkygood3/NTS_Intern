/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.exception;

public class ServerErrorException extends Exception {
	private static final long serialVersionUID = 1L;

	private final int ERROR_CODE = 500;
	private final String MESSAGE = "서버 내부에서 에러가 발생했습니다.";

	public ServerErrorException(Exception e) {
		super(e);
	}

	public int getERROR_CODE() {
		return ERROR_CODE;
	}

	public String getMESSAGE() {
		return MESSAGE;
	}

}
