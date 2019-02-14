package com.nts.reservation.dto.main;

/**
 * 메인 페이지 로드시 /api/categories 요청에 Category 정보를 담는 DTO 
 */
public class MainCategory {
	private Integer id;
	private String name;
	private Integer count;

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
