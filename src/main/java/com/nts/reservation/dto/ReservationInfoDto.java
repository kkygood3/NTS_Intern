/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dto;

import java.util.Date;

/**
 * @author 육성렬
 */
public class ReservationInfoDto {
	private Boolean cancelYn;
	private Date createDate;
	private DisplayInfoDto displayInfo;
	private Long displayInfoId;
	private Date modifyDate;
	private Long productId;
	private Date reservationDate;
	private String reservationEmail;
	private Long reservationInfoId;
	private String reservationName;
	private String reservationTel;
	private Integer totalPrice;

	public Boolean getCancelYn() {
		return cancelYn;
	}

	public void setCancelYn(Boolean cancelYn) {
		this.cancelYn = cancelYn;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public DisplayInfoDto getDisplayInfo() {
		return displayInfo;
	}

	public void setDisplayInfo(DisplayInfoDto displayInfo) {
		this.displayInfo = displayInfo;
	}

	public Long getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(Long displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	public String getReservationEmail() {
		return reservationEmail;
	}

	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}

	public Long getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(Long reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
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

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "ReservationInfoDto [cancelYn=" + cancelYn + ", createDate=" + createDate + ", displayInfo="
			+ displayInfo + ", displayInfoId=" + displayInfoId + ", modifyDate=" + modifyDate + ", productId="
			+ productId + ", reservationDate=" + reservationDate + ", reservationEmail=" + reservationEmail
			+ ", reservationInfoId=" + reservationInfoId + ", reservationName=" + reservationName
			+ ", reservationTel=" + reservationTel + ", totalPrice=" + totalPrice + "]";
	}

}
