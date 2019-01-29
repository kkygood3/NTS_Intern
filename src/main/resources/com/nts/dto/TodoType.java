/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.dto;

public enum TodoType {
	TODO("TODO") {
		@Override
		public String getNextType() {
			return "DOING";
		}
	},
	DOING("DOING") {
		@Override
		public String getNextType() {
			return "DONE";
		}
	},
	DONE("DONE") {
		@Override
		public String getNextType() {
			return "DONE";
		}
	};

	private String todoType;

	private TodoType(String todoType) {
		this.todoType = todoType;
	}

	//다음 타입을 반환하는 메소드 DONE은 사용할 필요가 없다.
	public abstract String getNextType();

	public String getTodoType() {
		return todoType;
	}

}
