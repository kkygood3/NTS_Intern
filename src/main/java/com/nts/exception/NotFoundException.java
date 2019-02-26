/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.exception;

/**
 * @author 전연빈
 */
public class NotFoundException extends Exception{

	private static final long serialVersionUID = 1L;
	public NotFoundException(String message) {
		super(message);
	}
}
