/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.category.dto;

import java.util.List;

public class CategoryResponse {
	private List<Category> items;

	public List<Category> getItems() {
		return items;
	}

	public void setItems(List<Category> list) {
		this.items = list;
	}

	@Override
	public String toString() {
		return "CategoryResponse [items=" + items + "]";
	}
}
