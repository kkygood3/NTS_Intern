/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dto.response;

import java.util.List;

import com.nts.reservation.dto.ProductDto;

/**
 * Promotion 전송 양식을 위한 클래스
 * @author 육성렬
 */
public class PromotionResponseDto {
	private List<ProductDto> items;
	private int totalCount;

	public PromotionResponseDto(List<ProductDto> items, int totalCount) {
		this.items = items;
		this.totalCount = totalCount;
	}

	public List<ProductDto> getItems() {
		return items;
	}

	public int getTotalCount() {
		return totalCount;
	}

}
