package com.nts.dto;

public class CategoryApiView {
	private long id;
	private String name;
	private long count;

	public CategoryApiView() {
		super();
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

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "CategoryApiView [id=" + id + ", name=" + name + ", count=" + count + "]";
	}
}
