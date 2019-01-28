/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.todolist.common;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public enum TodoType {

	TODO("TODO"),
	DOING("DOING"),
	DONE("DONE");

	private String currentType;

	private TodoType(String currentType) {
		this.currentType = currentType;
	}

	public String getValue() {
		return currentType;
	}

	public String getAfterType() {
		String afterType = "";

		if (TODO.equals(this)) {
			afterType = DOING.getValue();
		} else if (DOING.equals(this)) {
			afterType = DONE.getValue();
		}
		return afterType;
	}

}
