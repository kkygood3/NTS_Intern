/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.model;

public class ProductRequirer {

	private int categoryId;

	private int start;

	public ProductRequirer() {}

	public ProductRequirer(int categoryId, int start) {
		this.categoryId = categoryId;
		this.start = start;
	}

	@Override
	public String toString() {
		return "ProductRequirer [categoryId=" + categoryId + ", start=" + start + "]";
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}
}
