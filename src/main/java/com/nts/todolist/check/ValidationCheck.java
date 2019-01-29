/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.todolist.check;

import java.io.IOException;
import java.util.regex.Pattern;

import com.nts.todolist.common.TodoType;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public class ValidationCheck {
	public static boolean isCorrentValue(String title, String name, String sequenceString)
		throws IOException {
		if (!isCorrectTitle(title)) {
			//
			System.out.println("TodoAddServlet.java 제목이 올바르지 않습니다.");
			return false;
		}
		if (!isCorrectName(name)) {
			//
			System.out.println("TodoAddServlet.java 이름이 올바르지 않습니다.");
			return false;
		}
		if (!isCorrectSequence(sequenceString)) {
			//
			System.out.println("TodoAddServlet.java 우선순위가 올바르지 않습니다.");
			return false;
		}
		return true;
	}

	public static boolean isCorrentValue(Long id, String typeString) {
		if (!isCorrectId(id)) {
			//
			System.out.println("TodoAddServlet.java 아이디가 올바르지 않습니다.");
		}
		if (!isCorrectType(typeString)) {
			//
			System.out.println("TodoAddServlet.java 타입이 올바르지 않습니다.");
		}
		return true;
	}

	public static boolean isCorrectTitle(String title) {
		if (title.isEmpty() || title.length() > 24) {
			return false;
		}
		// 공백을 포함한 영문, 한글, 아라비안 숫자로 구성된 문자열
		if (Pattern.matches("^[a-zA-Z가-힣0-9\\s]*$", title)) {
			return true;
		}
		return false;
	}

	public static boolean isCorrectName(String name) {
		if (name.isEmpty() || name.length() > 17) {
			return false;
		}
		// 공백을 포함한 영문, 한글, 아라비안 숫자로 구성된 문자열
		if (Pattern.matches("^[a-zA-Z가-힣0-9\\s]*$", name)) {
			return true;
		}
		return false;
	}

	public static boolean isCorrectSequence(String sequenceString) {
		if (sequenceString.isEmpty()) {
			return false;
		}
		if (Pattern.matches("^[1-3]$", sequenceString)) {
			return true;
		}
		return false;
	}

	public static boolean isCorrectId(Long id) {
		if (id == 0) {
			return false;
		}
		if (Pattern.matches("^[0-9]*$", Long.toString(id))) {
			return true;
		}
		return false;
	}

	public static boolean isCorrectType(String typeString) {
		if (typeString.isEmpty()) {
			return false;
		}
		for (TodoType todoType : TodoType.values()) {
			if (TodoType.valueOf(typeString) == todoType) {
				return true;
			}
		}
		return false;
	}
}
