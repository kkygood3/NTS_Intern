/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.todolist.common;

import java.util.Arrays;

/**
 * Todo의 type에 대한 enum class
 * @author yongjoon.Park
 */
public enum TodoStatus {

	TODO("TODO") {
		@Override
		public TodoStatus getNextStatus() {
			return DOING;
		}
	},
	DOING("DOING") {
		@Override
		public TodoStatus getNextStatus() {
			return DONE;
		}
	},
	DONE("DONE") {
		@Override
		public TodoStatus getNextStatus() {
			return DONE;
		}
	};

	private final String todoStatus;

	private TodoStatus(String todoStatus) {
		this.todoStatus = todoStatus;
	}

	public String getTodoStatus() {
		return todoStatus;
	}

	/**입력 받은 값이 TodoStatus Enum에 존재하는 지 확인
	 * @author yongjoon.Park
	 * @param type 해당 enum에 존재하는 지 확인할 type값
	 * @return 해당 type이 있을 경우 true, 없을 경우 false를 return한다.
	 */
	public static boolean isExistTodoStatus(String type) {
		return Arrays.asList(TodoStatus.values()).stream()
			.anyMatch(todoStatus -> todoStatus.getTodoStatus().equals(type));
	}
	
	/**현재 todoStatus값을 다음 단계의 todoStatus으로 반환
	 * @author yongjoon.Park
	 */
	public abstract TodoStatus getNextStatus();
}
