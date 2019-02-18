package com.nts.reservation.dto;

import java.util.Date;

public class ReservationDisplayItem {
	private long reservationId;
	private String description;
	private Date reservationDate;
	private boolean canceled;
	private String placeName;
	private long price;

	public long getReservationId() {
		return reservationId;
	}

	public void setReservationId(long reservationId) {
		this.reservationId = reservationId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	public boolean isCanceled() {
		return canceled;
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
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
		return "ReservationDisplayItem [reservationId=" + reservationId + ", description=" + description
			+ ", reservationDate=" + reservationDate + ", canceled=" + canceled + ", placeName=" + placeName
			+ ", price=" + price + "]";
	}
}
