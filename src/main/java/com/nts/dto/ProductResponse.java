/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.dto;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ProductResponse {
	private List<Product> items;
	private int totalCount;
	public List<Product> getItems() {
		return items;
	}
	public void setItems(List<Product> items) {
		this.items = items;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	@Override
	public String toString() {
		return "ProductResponse [items=" + items + ", totalCount=" + totalCount + "]";
	}
}
