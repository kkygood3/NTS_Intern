/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.dto.categorydto;

import java.util.List;
 

public class CategoryResponse {
	private List<Category> items;

	public CategoryResponse(List<Category> items) {
		this.items = items;
	}
	
	public List<Category> getItems() {
		return items;
	}

	public void setItems(List<Category> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "CategoryResponse [items=" + items + "]";
	}

}