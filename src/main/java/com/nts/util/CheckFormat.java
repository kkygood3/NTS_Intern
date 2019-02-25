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
	private static final String WINDOW_PATH = "(^([a-z]|[A-Z]):(?=\\\\(?![\\0-\\37<>:\"/\\\\|?*])|\\/(?![\\0-\\37<>:\"/\\\\|?*])|$)|^\\\\(?=[\\\\\\/][^\\0-\\37<>:\"/\\\\|?*]+)|^(?=(\\\\|\\/)$)|^\\.(?=(\\\\|\\/)$)|^\\.\\.(?=(\\\\|\\/)$)|^(?=(\\\\|\\/)[^\\0-\\37<>:\"/\\\\|?*]+)|^\\.(?=(\\\\|\\/)[^\\0-\\37<>:\"/\\\\|?*]+)|^\\.\\.(?=(\\\\|\\/)[^\\0-\\37<>:\"/\\\\|?*]+))((\\\\|\\/)[^\\0-\\37<>:\"/\\\\|?*]+|(\\\\|\\/)$)*()$";
	private static final String UNIX_PATH = "^\\/$|(^(?=\\/)|^\\.|^\\.\\.)(\\/(?=[^/\\0])[^/\\0]+)*\\/?$";
	
	public static boolean validateEmailFormat(String email) {
		return Pattern.matches(EMAIL_PATTERN, email);
	}
	
	public static boolean validatePhoneNumberFormat(String phoneNumber) {
		return Pattern.matches(PHONE_NUMBER_PATTERN, phoneNumber);
	}
	
	public static boolean validateDateYYYYMMDDHipen(String date) {
		return Pattern.matches(DATE_PATTERN_YYYY_MM_DD_HIPEN, date);
	}
	
	public static boolean validateWindowPath(String path) {
		return Pattern.matches(WINDOW_PATH, path);
	}
	
	public static boolean validateUnixPath(String path) {
		return Pattern.matches(UNIX_PATH, path);
	}
}
