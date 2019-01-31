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
public class InputFormValidator {

	private static final int INPUT_MAX_LENGTH = 24;

	public static boolean isNotVaild(String title, String name, String sequence) {
		Pattern pattern = Pattern.compile("[123]");
		Matcher matcher = pattern.matcher(sequence);

		// 들어온 값 중에 null이 포함되어 있는 경우
		if (title == null || name == null || sequence == null) {
			System.out.printf("입력값 (%s, %s, %s) 형식이 잘못되었습니다.\n", title, name, sequence);
			return true;
		}

		// name, title이 공백일 경우.
		if (name.isEmpty() || title.isEmpty()) {
			System.out.printf("입력값 (title, name) (%s, %s)에 공백이 올 수 없습니다.\n", title, name);
			return true;
		}

		// 제목, 이름이 24자를 초과할 경우.
		if (title.length() > INPUT_MAX_LENGTH || name.length() > INPUT_MAX_LENGTH) {
			System.out.printf("입력값 (title, name) (%s, %s)이 24자를 초과하였습니다.\n", title, name);
			return true;
		}

		// sequence 1,2,3 이외의 값일 경우.
		if (!matcher.find()) {
			System.out.printf("우선순위 :%s 형식이 잘못되었습니다.\n", sequence);
			return true;
		}

		return false;
	}

	public static boolean isNotVaild(String id, String type) {
		Pattern pattern = Pattern.compile("[(^[0-9]*$)]");
		Matcher matcher = pattern.matcher(id);

		// type 형식이 null이거나 공백일 경우.
		if (type == null || type.isEmpty()) {
			System.out.printf("입력값 type : %s 형식이 잘못되었습니다.\n", type);
			return true;
		}

		// id값이 숫자가 아닌경우.
		if (!matcher.find()) {
			System.out.printf("입력값 id : %s 형식이 잘못되었습니다.\n", id);
			return true;
		}

		// type이 ENUM형식과 맞지 않는 경우.
		try {
			TodoType.valueOf(type);
		} catch (IllegalArgumentException e) {
			System.out.println("type의 형식이 잘못되었습니다. type : " + type);
			return true;
		}

		return false;
	}
}
