/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto.response;

import java.util.List;

import com.nts.reservation.dto.ProductDto;

/**
 * 상품 정보들과 카테고리에 해당하는 전시상품들의 개수를 담는 DTO 클래스
 * @author jinwoo.bae
 */
public class ProductResponseDto {
	private List<ProductDto> products;
	private int totalCount;

	public ProductResponseDto(List<ProductDto> products, int totalCount) {
		this.products = products;
		this.totalCount = totalCount;
	}

	public List<ProductDto> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDto> products) {
		this.products = products;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	@Override
	public String toString() {
		return "ProductResponseDto [products=" + products + ", totalCount=" + totalCount + "]";
	}

}
