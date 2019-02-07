package com.nts.project3.dto;

/**
 * 메인 페이지 출력을 위한 /api/categories 요청을 위한 DTO 
 */
public class MainCategory {
	private int id;
	private String name;
	private int count;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
