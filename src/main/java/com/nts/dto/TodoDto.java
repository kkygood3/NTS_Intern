/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.dto;

/**
 * todo table의 정보와 대응하는 컨테이너 클래스 dto
 * @author 박우성
 */
public class TodoDto{
	private Integer id;
	private String title;
	private String name;
	private int sequence;
	private String type;
	private String regdate;

	/**
	 * type UPDATE 수행을 위한 생성자
	 */
	public TodoDto(Integer id, String type) {
		this.id = id;
		this.type = type;
	}
	
	/**
	 * INSERT 수행을 위한 생성자
	 */
	public TodoDto(String title, String name, int sequence) {
		this.title = title;
		this.name = name;
		this.sequence = sequence;
	}
	
	/**
	 * getTodo를 위한 생성자
	 */
	public TodoDto(Integer id, String title, String name, int sequence, String type, String regdate) {
		super();
		this.id = id;
		this.title = title;
		this.name = name;
		this.sequence = sequence;
		this.type = type;
		this.regdate = regdate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
		return title;
	}
}
