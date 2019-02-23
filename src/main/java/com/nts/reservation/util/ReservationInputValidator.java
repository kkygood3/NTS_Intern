package com.nts.reservation.util;

import java.util.regex.Pattern;

/**
 * 사용자 입력 검증하는 클래스
 * @author si yoon
 *
 */
public class ReservationInputValidator {
	private ReservationInputValidator() {}

	public static boolean isValidEmail(String email) {
		String pattern = "(?i)^[a-z][-\\.\\w]*@[a-z][-\\.\\w]*\\.[a-z]{2,3}$";
		return Pattern.matches(pattern, email);
	}

	public static boolean isValidTel(String tel) {
		String pattern = "^01[016-9]-[0-9]{3,4}-[0-9]{4}$";
		return Pattern.matches(pattern, tel);
	}

	public static boolean isValidName(String name) {
		String pattern = "^([가-힣]{2,})$|^(([A-Z][a-z]*\\s)+[A-Z][a-z]*)$";
		return Pattern.matches(pattern, name);
	}
	
	public static boolean isValidReservationInfo(String name, String tel, String email) {
		return isValidEmail(email) & isValidTel(tel) & isValidName(name);
	}
}
