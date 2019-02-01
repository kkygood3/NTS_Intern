/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dto.response;

import java.util.List;

import com.nts.reservation.dto.CategoryDto;

/**
 * 카테고리 리스트 전송 양식을 위한 클래스
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
