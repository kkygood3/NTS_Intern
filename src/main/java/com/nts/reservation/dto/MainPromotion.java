/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dto;

public class MainPromotion {
	private Integer id;
	private Integer productId;
	private String productImageUrl;

	public Integer getId() {
		return id;
	}

	public Integer getProductId() {
		return productId;
	}

	public String getProductImageUrl() {
		return productImageUrl;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}

	@Override
	public String toString() {
		return "MainPromotion [id=" + id + ", productId=" + productId + ", productImageUrl=" + productImageUrl + "]";
	}
}
