/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.todolist.dto;

/**
 * @author yongjoon.Park
 */
public class TodoDto {
	private long id;
	private String title;
	private String name;
	private int sequence;
	private String type;
	private String regdate;

	public TodoDto() {}
	
	public TodoDto(String title, String name, int sequence) {
		this.title = title;
		this.name = name;
		this.sequence = sequence;
	}

	public TodoDto(long id, String title, String name, int sequence, String type, String regdate) {
		this.id = id;
		this.title = title;
		this.name = name;
		this.sequence = sequence;
		this.type = type;
		this.regdate = regdate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", title=" + title + ", name=" + name + ", sequence=" + sequence + ", type=" + type
			+ ", regdate=" + regdate + "]";
	}

}
