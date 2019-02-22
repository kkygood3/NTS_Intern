/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.reservation.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public class ReservationPrice {
	private int count;
	private int productPriceId;
	private int reservationInfoId;
	private int reservationInfoPriceId;
	private int discountRate;
	private ReservationPriceType priceTypeName;
	private String priceTypeLabel;
	private int price;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getProductPriceId() {
		return productPriceId;
	}

	public void setProductPriceId(int productPriceId) {
		this.productPriceId = productPriceId;
	}

	public int getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(int reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public int getReservationInfoPriceId() {
		return reservationInfoPriceId;
	}

	public void setReservationInfoPriceId(int reservationInfoPriceId) {
		this.reservationInfoPriceId = reservationInfoPriceId;
	}

	public int getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(int discountRate) {
		this.discountRate = discountRate;
	}

	public ReservationPriceType getPriceTypeName() {
		return priceTypeName;
	}

	public void setPriceTypeName(ReservationPriceType priceTypeName) {
		this.priceTypeName = priceTypeName;
	}

	public String getPriceTypeLabel() {
		return priceTypeLabel;
	}

	public void setPriceTypeLabel(String priceTypeLabel) {
		this.priceTypeLabel = priceTypeLabel;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
