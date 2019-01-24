/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.exception;

/**
 *
 * @description : DB Access Exception
 * @filename : DataAccessException.java
 * @package : com.nts.exception
 * @author : Seokhyeon Choi
 */
public class DataAccessException extends Exception {

	private static final String ERROR_MESSAGE = "Can not access to data";

	public DataAccessException(Exception e) {
		super(e);
	}

	public String getErrorMessage() {
		return ERROR_MESSAGE;
	}
}
