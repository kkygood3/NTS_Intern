/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.category.dto;

import java.util.List;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public class CategoryResponse {
	private List<Category> items;

	public List<Category> getItems() {
		return items;
	}

	public void setItems(List<Category> items) {
		this.items = items;
	}
}
