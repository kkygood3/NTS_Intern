/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.dto;

import com.nts.type.TodoType;

/**
 * @author 전연빈
 */
public class TodoDto {

	private long id; // 등록 ID
	private String personName; // 등록자 이름
	private String regDate; // 등록 일시
	private int sequence; // 우선 순위
	private String title; // todo 제목
	private String type; // type ( todo, doing, done ) 대문자 형식

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
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
		return "TodoDto [id=" + id + ", personName=" + personName + ", regDate=" + regDate + ", sequence=" + sequence
			+ ", title="
			+ title + ", type=" + type + "]";
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

}
