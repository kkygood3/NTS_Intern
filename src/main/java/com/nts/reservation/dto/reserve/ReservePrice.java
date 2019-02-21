/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto.reserve;

/**
 * reserve 페이지 로드시 필요한 가격 정보 DTO
 */
public class ReservePrice {
	private Integer price;
	private ReservePriceType priceTypeName;
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

	public ReservePriceType getPriceTypeName() {
		return priceTypeName;
	}

	public void setPriceTypeName(ReservePriceType priceTypeName) {
		this.priceTypeName = priceTypeName;
	}

	public Integer getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(Integer discountRate) {
		this.discountRate = discountRate;
	}
}
