/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dto;

import java.util.List;

/*
 * @author 육성렬
 */
public class CategoryResponseDto {
	private List<CategoryDto> items;

	public CategoryResponseDto(List<CategoryDto> items) {
		this.items = items;
	}

	public List<CategoryDto> getItems() {
		return items;
	}
}
