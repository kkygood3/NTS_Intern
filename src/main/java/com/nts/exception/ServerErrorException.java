/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.exception;

public class ServerErrorException extends Exception {

	private final int ERROR_CODE;
	private final String MESSAGE;

	public ServerErrorException() {
		super();
		this.ERROR_CODE = 500;
		this.MESSAGE = "server error, sorry";
	}

	public ServerErrorException(Exception e) {
		super(e);
		this.ERROR_CODE = 500;
		this.MESSAGE = "server error, sorry";
	}

	public int getERROR_CODE() {
		return ERROR_CODE;
	}

	@Override
	public String getMessage() {
		return MESSAGE;
	}
}
