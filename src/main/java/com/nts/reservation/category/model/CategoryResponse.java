/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.category.model;

import java.util.ArrayList;
import java.util.List;

import com.nts.reservation.common.model.Response;

public class CategoryResponse extends Response {

	private List<Category> categoryList;

	public CategoryResponse() {
		categoryList = new ArrayList<>();
	}

	public CategoryResponse(List<Category> categoryList) {
		this.categoryList = categoryList;
	}

	public List<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}

}
