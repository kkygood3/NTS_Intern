/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.common.utils;

public class RegexPattern {
	public static final String EMAIL_REGEX = "\\w{4,}@\\w+\\.\\w+";
	public static final String NAME_REGEX = "[a-zA-Z가-힣]{2,}";
	public static final String TEL_REGEX = "^01[016-9]\\d{7,8}$";
}
