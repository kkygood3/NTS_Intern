package com.nts.reservation.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ReservationInfo {
	private long id;
	private long productId;
	private long displayInfoId;
	private String reservationName;
	private String reservationTel;
	private String reservationEmail;
	private Date reservationDate;
	private boolean cancelFlag;
	private Date createDate;
	private Date modifyDate;

	public ReservationInfo(UserReservationInput input) throws ParseException {
		this.productId = input.getProductId();
		this.reservationName = input.getName();
		this.reservationTel = input.getTelephone();
		this.reservationEmail = input.getEmail();
		this.reservationDate = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.ENGLISH).parse(input.getReservationDate());
		this.cancelFlag = false;
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

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	public boolean isCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(boolean cancelFlag) {
		this.cancelFlag = cancelFlag;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "ReservationInfo [id=" + id + ", productId=" + productId + ", displayInfoId=" + displayInfoId
			+ ", reservationName=" + reservationName + ", reservationTel=" + reservationTel + ", reservationEmail="
			+ reservationEmail + ", reservationDate=" + reservationDate + ", cancelFlag=" + cancelFlag + ", createDate="
			+ createDate + ", modifyDate=" + modifyDate + "]";
	}
}
