/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.reservation.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.nts.reservation.common.model.Regexp;

public class Reservation {

	private int productId;

	private int displayInfoId;

	@NotNull(message = "name is must not null")
	private String reservationName;

	@NotNull(message = "email is must not null")
	@Pattern(regexp = Regexp.EMAIL, message = "email is not vaild")
	private String reservationEmail;

	@NotNull(message = "tel is must not null")
	@Pattern(regexp = Regexp.Tel, message = "tel is not vaild")
	private String reservationTel;

	@NotNull(message = "date is must not null")
	private String reservationDate;

	private List<ReservationPrice> reservationPriceList;

	public Reservation() {
		reservationPriceList = new ArrayList<>();
	}

	@Override
	public String toString() {
		return "Reservation [productId=" + productId + ", displayInfoId=" + displayInfoId + ", reservationName="
			+ reservationName + ", reservationEmail=" + reservationEmail + ", reservationTel=" + reservationTel
			+ ", reservationDate=" + reservationDate + ", reservationPriceList=" + reservationPriceList + "]";
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}

	public String getReservationEmail() {
		return reservationEmail;
	}

	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}

	public String getReservationTel() {
		return reservationTel;
	}

	public void setReservationTel(String reservationTel) {
		this.reservationTel = reservationTel;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}

	public List<ReservationPrice> getReservationPriceList() {
		return reservationPriceList;
	}

	public void setReservationPriceList(List<ReservationPrice> reservationPriceList) {
		this.reservationPriceList = reservationPriceList;
	}

}
