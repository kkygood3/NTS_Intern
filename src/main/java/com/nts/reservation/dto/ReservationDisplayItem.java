package com.nts.reservation.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ReservationDisplayItem {
	private long productId;
	private long reservationInfoId;
	private String description;
	private Date reservationDate;
	private String reservationDateFormat;
	private boolean canceled;
	private String placeName;
	private long price;
	private Date modifyDate;

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

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	public String getReservationDateFormat() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy.M.dd.(E)", Locale.KOREAN);
		return format.format(reservationDate);
	}

	public void setReservationDateFormat(String reservationDateFormat) {
		this.reservationDateFormat = reservationDateFormat;
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

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "ReservationDisplayItem [productId=" + productId + ", reservationInfoId=" + reservationInfoId
				+ ", description=" + description + ", reservationDate=" + reservationDate + ", reservationDateFormat="
				+ reservationDateFormat + ", canceled=" + canceled + ", placeName=" + placeName + ", price=" + price
				+ ", modifyDate=" + modifyDate + "]";
	}
}
