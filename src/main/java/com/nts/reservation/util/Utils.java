package com.nts.reservation.util;

import java.util.regex.Pattern;

/**
 * 프로젝트에서 일반적으로 쓰이는 메서드 모음
 * 
 * @author si yoon
 *
 */
public class Utils {
	private Utils() {
	}

	/**
	 * 빈문자열 체크
	 * 
	 * @param str 검사할 문자열
	 * @return 빈문자열인지 아닌지
	 */
	public static boolean isEmpty(String str) {
		String emptyString = "^\\s*$";
		if (str == null || Pattern.matches(emptyString, str)) {
			return true;
		}
		return false;
	}

	/**
	 * null인지 체크
	 * 
	 * @param obj 체크할 객체
	 * @return null인지 아닌지
	 */
	public static boolean isNull(Object obj) {
		if (obj == null) {
			return true;
		}
		return false;
	}
}
