/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.util;

import java.util.regex.Pattern;

/**
 * @author 전연빈
 */
public class CheckFormat {

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String PHONE_NUMBER_PATTERN = "01[016789]-[0-9]{3,4}-[0-9]{4}";
	private static final String DATE_PATTERN_YYYY_MM_DD_HIPEN = "[0-9]{4}\\-[0-9]{2}\\-[0-9]{2}";

	private CheckFormat() {
	}

	private static class TodoServiceLazyHolder {
		private static final CheckFormat INSTANCE = new CheckFormat();
	}

	public static CheckFormat getInstance() {
		return TodoServiceLazyHolder.INSTANCE;
	}

	public boolean validateEmailFormat(String email) {
		return Pattern.matches(EMAIL_PATTERN, email);
	}
	
	public boolean validatePhoneNumberFormat(String phoneNumber) {
		return Pattern.matches(PHONE_NUMBER_PATTERN, phoneNumber);
	}
	
	public boolean validateDateYYYYMMDDHipen(String date) {
		return Pattern.matches(DATE_PATTERN_YYYY_MM_DD_HIPEN, date);
	}
}
