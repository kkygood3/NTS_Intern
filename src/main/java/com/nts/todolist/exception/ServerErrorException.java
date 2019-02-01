/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.todolist.exception;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public class ServerErrorException extends Exception {
	private static final int ERROR_CODE = 500;
	private static final String MESSAGE = "서버에서 에러가 발생했습니다.";

	public ServerErrorException(Exception e) {
		super(e);
	}

	public int getErrorCode() {
		return ERROR_CODE;
	}

	@Override
	public String getMessage() {
		return MESSAGE;
	}

}
