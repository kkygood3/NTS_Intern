package com.nts.reservation.service.validation;

import java.util.regex.Pattern;

/**
 * 사용자 입력 검증하는 클래스
 * @author si yoon
 *
 */
public class Validator {
	private Validator() {}

	public static boolean validEmail(String email) {
		String pattern = "(?i)^[a-z][-\\.\\w]*@[a-z][-\\.\\w]*\\.[a-z]{2,3}$";
		return Pattern.matches(pattern, email);
	}

	public static boolean validTel(String tel) {
		String pattern = "^01[016-9]-[0-9]{3,4}-[0-9]{4}$";
		return Pattern.matches(pattern, tel);
	}

	public static boolean validName(String name) {
		String pattern = "^([가-힣]{2,})$|^(([A-Z][a-z]*\\s)+[A-Z][a-z]*)$";
		return Pattern.matches(pattern, name);
	}
	
	public static boolean isValidReservationInfo(String name, String tel, String email) {
		return validEmail(email) & validTel(tel) & validName(name);
	}
}
