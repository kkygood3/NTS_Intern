package com.nts.reservation.dto;

public class Categories {
	private Long count;
	private Long id;
	private String name;

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
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

	@Override
	public String toString() {
		return "Categories [count=" + count + ", id=" + id + ", name=" + name + "]";
	}
}
