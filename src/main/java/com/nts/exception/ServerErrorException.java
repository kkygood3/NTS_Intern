/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.exception;

public class ServerErrorException extends Exception {

	private final int ERROR_CODE = 500;
	private final String MESSAGE = "server error, sorry";

	public ServerErrorException(Exception e) {
		super(e);
	}

	public ServerErrorException() {
		super();
	}

	public int getERROR_CODE() {
		return ERROR_CODE;
	}

	@Override
	public String getMessage() {
		return MESSAGE;
	}
}
