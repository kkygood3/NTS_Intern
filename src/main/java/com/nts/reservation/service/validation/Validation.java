package com.nts.reservation.service.validation;

import java.util.regex.Pattern;

/**
 * 사용자 입력 검증하는 클래스
 * @author si yoon
 *
 */
public class Validation {
	private Validation() {}

	private static class ValidationLazyHolder {
		static final Validation INSTANCE = new Validation();
	}

	public static Validation getInstance() {
		return ValidationLazyHolder.INSTANCE;
	}

	public boolean validateEmail(String email) {
		String pattern = "(?i)^[a-z][-\\.\\w]*@[a-z][-\\.\\w]*\\.[a-z]{2,3}$";
		return Pattern.matches(pattern, email);
	}

	public boolean validateTel(String tel) {
		String pattern = "^01[016-9]-[0-9]{3,4}-[0-9]{4}$";
		return Pattern.matches(pattern, tel);
	}

	public boolean validateName(String name) {
		String pattern = "^([가-힣]{2,})$|^(([A-Z][a-z]*\\s)+[A-Z][a-z]*)$";
		return Pattern.matches(pattern, name);
	}
}
