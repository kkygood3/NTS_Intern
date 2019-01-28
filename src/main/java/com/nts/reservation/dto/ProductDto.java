/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dto;

/*
 * @author 육성렬
 */
public class ProductDto {
	private Long displayInfoId;
	private Long productId;
	private String placeName;
	private String productContent;
	private String productDescription;
	private String productImageUrl;

	public Long getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(Long displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getProductContent() {
		return productContent;
	}

	public void setProductContent(String productContent) {
		this.productContent = productContent;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductImageUrl() {
		return productImageUrl;
	}

	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = "./" + productImageUrl;
	}

	@Override
	public String toString() {
		return "ProductDto [displayInfoId=" + displayInfoId + ", productId=" + productId + ", placeName=" + placeName
			+ ", productContent=" + productContent + ", productDescription=" + productDescription + ", productImageUrl="
			+ productImageUrl + "]";
	}

}
