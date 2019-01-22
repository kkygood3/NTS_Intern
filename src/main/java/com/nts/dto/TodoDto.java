/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.dto;

/**
 * Todo 테이블 정보를 담는 클래스
 * @author jinwoo.bae
 */

public class TodoDto {
	public enum Type {
		TODO, DOING, DONE;

		public Type getNext() {
			if (ordinal() + 1 >= values().length) // 마지막 enum인경우 다음값으로 넘어가지않음 
				return this;
			return values()[ordinal() + 1];
		}
	}

	long id;
	String name;
	String regdate;
	int sequence;
	String title;
	Type type;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
}