/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.nts.dto.TodoType;

/**
 * 들어온 입력 값들의 형식을 확인하는 클래스
 * @author kkygood3
 */
public class checkInputForm {
	public static boolean isRightInputForm(String title, String name, String sequence) {
		Pattern p = Pattern.compile("[123]");
		Matcher m = p.matcher(sequence);

		// 제목, 이름이 24자를 초과하거나 비어있는 경우
		// sequence가 1,2,3 이외의 값일 경우.
		if (title.length() > 24 || title.isEmpty() || name.length() > 24 ||
			name.isEmpty() || !m.find()) {
			System.out.printf("입력값 (%s, %s, %s) 형식이 잘못되었습니다.\n", title, name, sequence);
			return true;
		}

		return false;
	}

	public static boolean isRightInputForm(String id, String type) {
		Pattern p = Pattern.compile("[(^[0-9]*$)]");
		Matcher m = p.matcher(id);

		// id값이 숫자가 아닌경우.
		// type이 ENUM형식과 맞지 않는 경우.
		if (!m.find() || type.isEmpty()) {
			System.out.printf("입력값 (%s, %s) 형식이 잘못되었습니다.\n", id, type);
			return true;
		}

		try {
			TodoType.valueOf(type);
		} catch (IllegalArgumentException e) {
			System.out.println("type의 형식이 잘못되었습니다. type : " + type);
		}

		return false;
	}
}
