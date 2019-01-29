package com.nts.todolist.common;

import java.util.regex.Pattern;

public class StringUtils {
	private static final int MAX_LENGTH = 24;
	private static final String SEQUENCE_PATTERN = "[123]";
	private static final String ID_PATTERN = "^[0-9]*$";

	/**
	 * 입력받은 Stirng 값이 양식에 맞는 값인 지 확인하는 method
	 * @author yongjoon.Park
	 * @param title 양식에 적합한지 확인할 title 값
	 * @param name 양식에 적합한지 확인할 name 값
	 * @param sequence 양식에 적합한지 확인할 sequence 값
	 * @return String 값이 null 혹은 빈값("") 혹은 24글자가 넘는다면 false를 반환
	 */
	public static boolean isValid(String title, String name, String sequence) {
		if (title == null || name == null || sequence == null) {
			return false;
		}

		int titleLength = title.length();
		if (titleLength == 0 || titleLength > MAX_LENGTH) {
			return false;
		}

		int nameLength = name.length();
		if (nameLength == 0 || nameLength > MAX_LENGTH) {
			return false;
		}

		if (!Pattern.matches(SEQUENCE_PATTERN, sequence)) {
			return false;
		}
		return true;
	}

	/**
	 * 입력받은 Stirng 값이 양식에 맞는 값인 지 확인하는 method
	 * @author yongjoon.Park
	 * @param id 양식에 적합한지 확인할 id 값
	 * @param type 양식에 적합한지 확인할 type 값
	 * @return String 값이 null 혹은  pattern에 맞지 않는다면 false를 반환
	 */
	public static boolean isValid(String id, String type) {
		if (id == null || type == null) {
			return false;
		}

		if (!Pattern.matches(ID_PATTERN, id)) {
			return false;
		}

		if (!TodoStatus.isExistTodoStatus(type)) {
			return false;
		}

		if (type.equals(TodoStatus.DONE.getTodoStatus())) {
			return false;
		}
		
		return true;
	}
}
