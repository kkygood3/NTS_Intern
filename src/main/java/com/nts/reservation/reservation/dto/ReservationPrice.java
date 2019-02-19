/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.reservation.dto;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public class ReservationPrice {
	private int count;
	private int productPriceId;
	private int reservationInfoId;
	private int reservationInfoPriceId;

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

	@Override
	public String toString() {
		return "ReservationPrice [count=" + count + ", productPriceId=" + productPriceId + ", reservationInfoId="
			+ reservationInfoId + ", reservationInfoPriceId=" + reservationInfoPriceId + "]";
	}
}
