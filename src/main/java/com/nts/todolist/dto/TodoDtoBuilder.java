/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.todolist.dto;

public class TodoDtoBuilder {
	private long id;
	private String title;
	private String name;
	private int sequence;
	private String type;
	private String regdate;

	public TodoDtoBuilder setId(long id) {
		this.id = id;
		return this;
	}

	public TodoDtoBuilder setTitle(String title) {
		this.title = title;
		return this;
	}

	public TodoDtoBuilder setName(String name) {
		this.name = name;
		return this;
	}

	public TodoDtoBuilder setSequence(int sequence) {
		this.sequence = sequence;
		return this;
	}

	public TodoDtoBuilder setType(String type) {
		this.type = type;
		return this;
	}

	public TodoDtoBuilder setRegdate(String regdate) {
		this.regdate = regdate;
		return this;
	}
	
	public TodoDto build() {
		return new TodoDto(id, title, name, sequence, type, regdate);
	}

}
