/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.todolist.dto;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public class TodoDto {
	private long id;
	private String title;
	private String name;
	private int sequence;
	private String type;
	private String regdate;

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getRegdate() {
		return regdate;
	}

	public int getSequence() {
		return sequence;
	}

	public String getTitle() {
		return title;
	}

	public String getType() {
		return type;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRegdate(String string) {
		this.regdate = string;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "TodoDto [id=" + id + ", name=" + name + ", regdate=" + regdate + ", sequence=" + sequence + ", title="
			+ title + ", type=" + type + "]";
	}
}
