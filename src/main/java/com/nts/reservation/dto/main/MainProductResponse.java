/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto.main;

import java.util.List;

/**
 * 메인 페이지 로드시 /api/products 출력 객체
 */
public class MainProductResponse {
	private List<MainProduct> productList;
	private Integer count;

	public MainProductResponse(List<MainProduct> productList, Integer count) {
		this.productList = productList;
		this.count = count;
	}

	public List<MainProduct> getProductList() {
		return productList;
	}

	public void setProductList(List<MainProduct> productList) {
		this.productList = productList;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
