/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.product.dto;

import java.util.List;

public class ProductResponse {
	private List<Product> items;
	private int totalCount;
	public List<Product> getItems() {
		return items;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setItems(List<Product> items) {
		this.items = items;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	@Override
	public String toString() {
		return "ProductResponse [items=" + items + ", totalCount=" + totalCount + "]";
	}

}
