/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.dto.reservationdto;

public class ReservationPrice {
	private int count;
	private int productPriceId;
	private int reservationInfoId;
	private int id;
	private int price;
	private double discountRate;
	private String priceTypeName;

	public double getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}

	public String getPriceTypeName() {
		return priceTypeName;
	}

	public void setPriceTypeName(String priceTypeName) {
		this.priceTypeName = priceTypeName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getReservationInfoPriceId() {
		return id;
	}

	public void setReservationInfoPriceId(int reservationInfoPriceId) {
		this.id = reservationInfoPriceId;
	}

	@Override
	public String toString() {
		return "ReservationPrice [count=" + count + ", productPriceId=" + productPriceId + ", reservationInfoId="
				+ reservationInfoId + ", id=" + id + ", price=" + price + ", discountRate=" + discountRate
				+ ", priceTypeName=" + priceTypeName + "]";
	}
}
