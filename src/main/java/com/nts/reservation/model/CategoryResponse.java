/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.model;

import java.util.ArrayList;
import java.util.List;

public class CategoryResponse {

	private List<Category> item;

	public CategoryResponse() {
		item = new ArrayList<>();
	}

	public CategoryResponse(List<Category> item) {
		this.item = item;
	}

	public List<Category> getItem() {
		return item;
	}

	public void setItem(List<Category> item) {
		this.item = item;
	}

}
