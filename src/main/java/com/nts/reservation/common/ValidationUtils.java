/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.common;

import java.util.regex.Pattern;

import org.springframework.web.multipart.MultipartFile;

import com.nts.reservation.property.Properties;

/**
 * Reservation 프로젝트에서 사용하는 특정 형식 요청이 유효한지 검증하는 static method 클래스
 */
public class ValidationUtils {
	public static boolean validateEmail(String email) {
		return (email != null
			&& Pattern.matches(Properties.REG_EMAIL, email.trim()));
	}

	public static boolean validateTelephone(String telephone) {
		return (telephone != null
			&& Pattern.matches(Properties.REG_TELEPHONE, telephone.trim()));
	}

	public static boolean validateName(String name) {
		return (name != null
			&& Pattern.matches(Properties.REG_NAME, name));
	}

	public static boolean validateComment(String comment) {
		return (comment != null
			&& Pattern.matches(Properties.REG_COMMENT, comment));
	}

	public static boolean validateScore(int score) {
		return (score >= Properties.MIN_REVIEW_SCORE
			&& score <= Properties.MAX_REVIEW_SCORE);
	}

	public static boolean validateImage(MultipartFile imageFile) {
		String extName = imageFile.getContentType();
		for (final String allowName : Properties.ALLOW_FILE_EXTENSION) {
			if (extName.equalsIgnoreCase(allowName)) {
				return true;
			}
		}

		return false;
	}
}
