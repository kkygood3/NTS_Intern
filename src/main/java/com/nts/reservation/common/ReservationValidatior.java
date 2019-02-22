package com.nts.reservation.common;

import java.util.regex.Pattern;

import com.nts.reservation.property.CommonProperties;

/**
 * Reservation 프로젝트에서 사용하는 특정 형식 String이 유효한지 검증하는 Class
 * static method를 사용함
 */
public class ReservationValidatior {
	public static boolean validateEmail(String email) {
		return (email != null && email.length() > 0
			&& email.length() <= CommonProperties.MAX_EMAIL_LENGTH
			&& Pattern.matches(CommonProperties.REG_EMAIL, email.trim()));
	}

	public static boolean validateTelephone(String telephone) {
		return (telephone != null
			&& telephone.length() > 0
			&& Pattern.matches(CommonProperties.REG_TELEPHONE, telephone.trim()));
	}

	public static boolean validateName(String name) {
		return (name != null && name.length() > 0
			&& name.length() <= CommonProperties.MAX_NAME_LENGTH);
	}
}
