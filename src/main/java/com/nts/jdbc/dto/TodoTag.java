/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.jdbc.dto;

/**
 * @author 육성렬
 */
public enum TodoTag {
	TODO("TODO"),
	DOING("DOING"),
	DONE("DONE");

	private String tag;

	TodoTag(String tag) {
		this.tag = tag;
	}

	public String getTodoTag() {
		return tag;
	}

	public String getNext() {
		if (this == TODO) {
			return DOING.tag;
		} else {
			return DONE.tag;
		}
	}
}
