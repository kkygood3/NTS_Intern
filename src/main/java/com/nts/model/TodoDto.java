/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.model;

import com.nts.enums.TodoType;

public class TodoDto {

	private long id;
	private String name;
	private String regdate;
	private int sequence;
	private String title;
	private String type; // TodoType enum을 사용하는편이 더 명확하고 효율적으로 생각하지만, 요구사항명세가 String형으로 되어있음. 차후 수정하면 좋을듯..

	public void changeTypeToNext() {
		this.type = TodoType.valueOf(this.type).getNext();
	}

	@Override
	public String toString() {
		return "TodoDto [id=" + id + ", name=" + name + ", regdate=" + regdate + ", sequence=" + sequence + ", title="
			+ title + ", type=" + type + "]";
	}

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
