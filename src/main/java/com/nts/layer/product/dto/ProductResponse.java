/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.layer.product.dto;

import java.util.List;

/**
 * @author 전연빈
 */
public class ProductResponse {
	private List<Product> items; 	// 상품 모델
	private int totalCount; 		// 카테고리에 해당하는 전체 상품 수이

	public List<Product> getItems() {
		return items;
	}

	public void setItems(List<Product> items) {
		this.items = items;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalCount() {
		return totalCount;
	}
}
