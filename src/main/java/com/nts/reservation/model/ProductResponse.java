/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.model;

import java.util.ArrayList;
import java.util.List;

public class ProductResponse {

	private List<Product> item;
	private int totalCount;

	public ProductResponse() {
		item = new ArrayList<>();
		totalCount = 0;
	}

	public ProductResponse(List<Product> item, int totalCount) {
		this.item = item;
		this.totalCount = totalCount;
	}

	public List<Product> getItem() {
		return item;
	}

	public void setItem(List<Product> item) {
		this.item = item;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

}
