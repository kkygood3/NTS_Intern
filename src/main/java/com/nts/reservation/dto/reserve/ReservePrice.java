/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto.reserve;

import com.nts.reservation.common.PriceType;

/**
 * reserve 페이지 로드시 필요한 가격 정보 DTO
 */
public class ReservePrice {
	private Integer price;
	private PriceType priceTypeName;
	private String priceTypeLabel;
	private Integer discountRate;

	public String getPriceTypeLabel() {
		return priceTypeLabel;
	}

	public void setPriceTypeLabel(String priceTypeLabel) {
		this.priceTypeLabel = priceTypeLabel;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public PriceType getPriceTypeName() {
		return priceTypeName;
	}

	public void setPriceTypeName(PriceType priceTypeName) {
		this.priceTypeName = priceTypeName;
	}

	public Integer getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(Integer discountRate) {
		this.discountRate = discountRate;
	}
}
