/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.model;

import java.util.ArrayList;
import java.util.List;

public class ProductResponse {

	private List<Product> productList;
	private int totalCount;

	public ProductResponse() {
		productList = new ArrayList<>();
	}

	public ProductResponse(List<Product> productList, int totalCount) {
		this.productList = productList;
		this.totalCount = totalCount;
	}

	@Override
	public String toString() {
		return "ProductResponse [productList=" + productList + ", totalCount=" + totalCount + "]";
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

}
