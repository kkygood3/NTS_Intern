/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto.main;

import java.util.List;

/**
 * 메인 페이지 로드시 /api/categories 출력 객체
 */
public class MainCategoryResponse {
	private List<MainCategory> categoryList;

	public MainCategoryResponse(List<MainCategory> categoryList) {
		this.categoryList = categoryList;
	}

	public List<MainCategory> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<MainCategory> categoryList) {
		this.categoryList = categoryList;
	}
}
