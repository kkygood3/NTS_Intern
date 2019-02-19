/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.reservation.dto;

import java.util.List;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public class ReservationParam {
	private int displayInfoId;
	private List<ReservationPrice> prices;
	private int productId;
	private String reservationEmail;
	private String reservationName;
	private String reservationTelephone;
	private String reservationYearMonthDay;

	public int getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public List<ReservationPrice> getPrices() {
		return prices;
	}

	public void setPrices(List<ReservationPrice> prices) {
		this.prices = prices;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getReservationEmail() {
		return reservationEmail;
	}

	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}

	public String getReservationTelephone() {
		return reservationTelephone;
	}

	public void setReservationTelephone(String reservationTelephone) {
		this.reservationTelephone = reservationTelephone;
	}

	public String getReservationYearMonthDay() {
		return reservationYearMonthDay;
	}

	public void setReservationYearMonthDay(String reservationYearMonthDay) {
		this.reservationYearMonthDay = reservationYearMonthDay;
	}

	@Override
	public String toString() {
		return "ReservationParam [displayInfoId=" + displayInfoId + ", productId=" + productId + ", reservationEmail="
			+ reservationEmail + ", reservationName=" + reservationName + ", reservationTelephone="
			+ reservationTelephone + ", reservationYearMonthDay=" + reservationYearMonthDay + "]";
	}
}
