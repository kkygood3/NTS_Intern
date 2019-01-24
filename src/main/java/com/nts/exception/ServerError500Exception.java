/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.exception;

/**
 * @desc Server Error 500 Exception
 * @author 전연빈
 */
public class ServerError500Exception extends Exception{

	private static final long serialVersionUID = 1L;
	
	public ServerError500Exception(String message) {
		super(message);
	}
}
