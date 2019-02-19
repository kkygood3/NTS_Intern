/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.annotation.Validated;

/**
 * 정규식 검사
 * @author jinwoo.bae
 */
@Validated
public class RegexValidator {
	public static Boolean isValid(String regex, String string) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(string);
		if (m.matches()) {
			return true;
		}
		return false;
	}
}
