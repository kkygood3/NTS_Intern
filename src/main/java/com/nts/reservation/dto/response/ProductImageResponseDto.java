/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto.response;

import java.util.List;

import com.nts.reservation.dto.ProductImageDto;

/**
 * 프로덕트별 이미지 response용 Dto
 * @author jinwoo.bae
 */
public class ProductImageResponseDto {
	List<ProductImageDto> productImages;
	int totalCount;
	String productDescription;

	public ProductImageResponseDto(List<ProductImageDto> productImages, int count, String productDescription) {
		this.productImages = productImages;
		this.totalCount = count;
		this.productDescription = productDescription;
	}

	public List<ProductImageDto> getProductImages() {
		return productImages;
	}

	public void setProductImages(List<ProductImageDto> productImages) {
		this.productImages = productImages;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

}
