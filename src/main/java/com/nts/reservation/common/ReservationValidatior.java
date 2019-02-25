/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.common;

import java.util.regex.Pattern;

import org.springframework.web.multipart.MultipartFile;

import com.nts.reservation.property.CommonProperties;

/**
 * Reservation 프로젝트에서 사용하는 특정 형식 String이 유효한지 검증하는 Class
 * static method를 사용함
 */
public class ReservationValidatior {
	public static boolean validateEmail(String email) {
		return (email != null
			&& email.length() > 0
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

	public static boolean validateComment(String comment) {
		int commentLength = comment.length();
		return (CommonProperties.MIN_COMMENT_LENGTH <= commentLength
			&& commentLength <= CommonProperties.MAX_COMMENT_LENGTH);
	}

	public static boolean validateScore(int score) {
		return (CommonProperties.MIN_REVIEW_SCORE <= score
			&& score <= CommonProperties.MAX_REVIEW_SCORE);
	}

	public static boolean validateImage(MultipartFile imageFile) {
		String extName = imageFile.getContentType();
		for (final String allowName : CommonProperties.ALLOW_FILE_EXTENSION) {
			if (extName.equalsIgnoreCase(allowName)) {
				return true;
			}
		}

		return false;
	}
}
