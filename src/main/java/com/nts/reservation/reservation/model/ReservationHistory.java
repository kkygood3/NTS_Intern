/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.reservation.model;

public class ReservationHistory {

	private int reservationId;
	private String productDescription;
	private String placeName;
	private String placeLot;
	private String placeStreet;
	private String reservationDate;
	private ReservationStatus reservationStatus;
	private int totalPrice;

	@Override
	public String toString() {
		return "ReservationHistory [reservationId=" + reservationId + ", productDescription=" + productDescription
			+ ", placeName=" + placeName + ", placeLot=" + placeLot + ", placeStreet=" + placeStreet
			+ ", reservationDate=" + reservationDate + ", reservationStatus=" + reservationStatus + ", totalPrice="
			+ totalPrice + "]";
	}

	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getPlaceLot() {
		return placeLot;
	}

	public void setPlaceLot(String placeLot) {
		this.placeLot = placeLot;
	}

	public String getPlaceStreet() {
		return placeStreet;
	}

	public void setPlaceStreet(String placeStreet) {
		this.placeStreet = placeStreet;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}

	public ReservationStatus getReservationStatus() {
		return reservationStatus;
	}

	public void setReservationStatus(ReservationStatus reservationStatus) {
		this.reservationStatus = reservationStatus;
	}

	public void setReservationStatus(int statusCode) {
		this.reservationStatus = ReservationStatus.valueOf(statusCode);
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

}
