/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.exception;

/**
 * @author 육성렬
 */
public class CustomFileNotFoundException extends Exception {

	public CustomFileNotFoundException(String url) {
		super("존재하지 않는 파일입니다. [Url] : " + url);
	}

	public CustomFileNotFoundException(String name, Object value) {
		super("존재하지 않는 파일입니다. [name] : " + name + " [value] : " + value);
	}
}
