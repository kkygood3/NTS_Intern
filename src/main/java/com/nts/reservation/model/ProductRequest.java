/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.model;

import javax.validation.constraints.Min;

public class ProductRequest {

	private static final int ALL_CATEGORY = 0;

	private int categoryId;

	@Min(value = 0, message = "start value must positive number")
	private int start;

	public boolean isAllCategory() {
		return this.categoryId == ALL_CATEGORY;
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
