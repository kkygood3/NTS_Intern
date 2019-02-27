package com.nts.reservation.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ReservationDisplayItem {
	private long productId;
	private long reservationInfoId;
	private String description;
	private LocalDateTime reservationDate;
	private String placeName;
	private long price;

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public long getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(long reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReservationDate() {
		return reservationDate.format(DateTimeFormatter.ofPattern("yyyy.M.dd.(E)", Locale.KOREAN));
	}

	public void setReservationDate(LocalDateTime reservationDate) {
		this.reservationDate = reservationDate;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ReservationDisplayItem [productId=" + productId + ", reservationInfoId=" + reservationInfoId
				+ ", description=" + description + ", reservationDate=" + reservationDate + ", placeName=" + placeName
				+ ", price=" + price + "]";
	}
}
