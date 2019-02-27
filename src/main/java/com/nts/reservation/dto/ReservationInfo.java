package com.nts.reservation.dto;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReservationInfo {
	private long id;
	private long productId;
	private long displayInfoId;
	private String reservationName;
	private String reservationTel;
	private String reservationEmail;
	private LocalDateTime reservationDate;
	private boolean cancelFlag;
	private LocalDateTime createDate;
	private LocalDateTime modifyDate;

	public ReservationInfo(UserReservationInput input) throws ParseException {
		this.productId = input.getProductId();
		this.reservationName = input.getName();
		this.reservationTel = input.getTelephone();
		this.reservationEmail = input.getEmail();
		this.reservationDate = LocalDateTime.parse(input.getReservationDate() + " 00:00:00", DateTimeFormatter.ofPattern("yyyy.M.d. HH:mm:ss"));
		this.cancelFlag = false;
		this.createDate = LocalDateTime.now();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public long getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(long displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}

	public String getReservationTel() {
		return reservationTel;
	}

	public void setReservationTel(String reservationTel) {
		this.reservationTel = reservationTel;
	}

	public String getReservationEmail() {
		return reservationEmail;
	}

	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}

	public LocalDateTime getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(LocalDateTime reservationDate) {
		this.reservationDate = reservationDate;
	}

	public boolean isCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(boolean cancelFlag) {
		this.cancelFlag = cancelFlag;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(LocalDateTime modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "ReservationInfo [id=" + id + ", productId=" + productId + ", displayInfoId=" + displayInfoId
				+ ", reservationName=" + reservationName + ", reservationTel=" + reservationTel + ", reservationEmail="
				+ reservationEmail + ", reservationDate=" + reservationDate + ", cancelFlag=" + cancelFlag
				+ ", createDate=" + createDate + ", modifyDate=" + modifyDate + "]";
	}
}
