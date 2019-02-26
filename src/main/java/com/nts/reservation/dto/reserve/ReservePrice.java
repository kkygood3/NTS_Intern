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
	private String priceTypeLabel;
	private Integer discountRate;
	private Integer priceInfoId;
	private ReservePriceType priceTypeName;
	private Integer count;
	private Integer reservationInfoId;

	public ReservePriceType getPriceTypeName() {
		return priceTypeName;
	}

	public void setPriceTypeName(ReservePriceType priceTypeName) {
		this.priceTypeName = priceTypeName;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(Integer reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public Integer getPriceInfoId() {
		return priceInfoId;
	}

	public void setPriceInfoId(Integer priceInfoId) {
		this.priceInfoId = priceInfoId;
	}

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

	public Integer getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(Integer discountRate) {
		this.discountRate = discountRate;
	}
}
