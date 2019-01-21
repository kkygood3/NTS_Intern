/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.todo.dto;

public class TodoDto {
	private Long id;
	private String name;
	private String regdate;
	private int sequence;
	private String title;
	private String type;

	public TodoDto(String title, String name, int sequence) {
		this.title = title;
		this.name = name;
		this.sequence = sequence;
	}

	public TodoDto(Long id, String title, String name, int sequence, String type, String regdate) {
		this.id = id;
		this.title = title;
		this.name = name;
		this.sequence = sequence;
		this.type = type;
		this.regdate = regdate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	@Override
	public String toString() {
		return "Todo [Id=" + id + ", title=" + title + ", name=" + name + ", sequence=" + sequence + ", type=" + type
			+ "]";
	}

}