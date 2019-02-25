/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.reservation.model;

public class ReservationPrice {

	private int productPriceId;
	private int count;

	@Override
	public String toString() {
		return "ReservationPrice [productPriceId=" + productPriceId + ", count=" + count + "]";
	}

	public int getProductPriceId() {
		return productPriceId;
	}

	public void setProductPriceId(int productPriceId) {
		this.productPriceId = productPriceId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean isReserved() {
		return this.count > 0;
	}

}
