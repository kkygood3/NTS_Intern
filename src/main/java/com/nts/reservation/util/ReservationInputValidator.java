package com.nts.reservation.util;

import java.util.regex.Pattern;

/**
 * 예약 사용자 입력 검증하는 클래스
 * 
 * @author si yoon
 *
 */
public class ReservationInputValidator {
	private ReservationInputValidator() {
	}

	public static boolean isValidEmail(String email) {
		if (email == null)
			return false;
		String pattern = "(?i)^[a-z][-\\.\\w]*@[a-z][-\\.\\w]*\\.[a-z]{2,3}$";
		return Pattern.matches(pattern, email);
	}

	public static boolean isValidTel(String telephone) {
		if (telephone == null)
			return false;
		String pattern = "^01[016-9]-[0-9]{3,4}-[0-9]{4}$";
		return Pattern.matches(pattern, telephone);
	}

	public static boolean isValidName(String name) {
		if (name == null)
			return false;
		String pattern = "^([가-힣]{2,})$|^(([A-Z][a-z]*\\s)+[A-Z][a-z]*)$";
		return Pattern.matches(pattern, name);
	}

	public static boolean isValidReservationInfo(String name, String telephone, String email) {
		return isValidEmail(email) & isValidTel(telephone) & isValidName(name);
	}
}
