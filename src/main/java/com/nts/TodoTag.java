/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts;

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
}
