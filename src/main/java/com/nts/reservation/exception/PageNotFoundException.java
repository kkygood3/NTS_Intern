/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.exception;

/**
 * @author 육성렬
 */
public class PageNotFoundException extends Exception {

	public PageNotFoundException(String url) {
		super("존재하지 않는 페이지에 대한 요청입니다. [url] : " + url);
	}
}
