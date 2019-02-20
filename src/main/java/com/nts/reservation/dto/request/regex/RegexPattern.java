/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dto.request.regex;

/**
 * @author 육성렬
 */
public class RegexPattern {
	public static final String EMAIL_REGEX = "^[a-zA-Z0-9]{1,20}@[a-zA-Z0-9]{1,20}\\.[a-zA-Z]{1,6}\\.[a-zA-Z]{1,6}$"
		+ "|^[a-zA-Z0-9]{1,20}@[a-zA-Z0-9]{1,20}\\.[a-zA-Z]{1,6}$";
	public static final String RESERVER_NAME_REGEX = "^[가-힣A-Za-z0-9]{1,17}$";
	public static final String PHONE_NUMBER_REGEX = "01\\d\\-\\d{3,4}\\-\\d{4}$";
}
