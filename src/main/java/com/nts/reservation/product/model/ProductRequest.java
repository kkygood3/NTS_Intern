/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.product.model;

import javax.validation.constraints.Min;

public class ProductRequest {

	private int categoryId;

	@Min(value = 0, message = "start value must be positive number")
	private int start;

	@Override
	public String toString() {
		return "ProductRequest [categoryId=" + categoryId + ", start=" + start + "]";
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
