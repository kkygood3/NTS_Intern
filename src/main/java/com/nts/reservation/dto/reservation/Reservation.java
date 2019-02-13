package com.nts.reservation.dto.reservation;

/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 */
import java.time.LocalDateTime;

import com.nts.reservation.dto.common.DisplayInfo;

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 *
 */

public class Reservation {
	private Boolean cancelYn;
	private LocalDateTime createDate;
	private DisplayInfo displayInfo;
	private Long displayInfoId;
	private LocalDateTime modifydate;
	private Long productId;
	private String reservationDate;
	private String reservationEmail;
	private Long reservationInfoId;
	private String reservationName;
	private String reservationTelephone;
	private Integer totalPrice;

	public Boolean getCancelYn() {
		return cancelYn;
	}

	public void setCancelYn(Boolean cancelYn) {
		this.cancelYn = cancelYn;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public DisplayInfo getDisplayInfo() {
		return displayInfo;
	}

	public void setDisplayInfo(DisplayInfo displayInfo) {
		this.displayInfo = displayInfo;
	}

	public Long getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(Long displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public LocalDateTime getModifydate() {
		return modifydate;
	}

	public void setModifydate(LocalDateTime modifydate) {
		this.modifydate = modifydate;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(String reservationDate) {
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

	public String getReservationTelephone() {
		return reservationTelephone;
	}

	public void setReservationTelephone(String reservationTelephone) {
		this.reservationTelephone = reservationTelephone;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "Reservation [cancelYn=" + cancelYn + ", createDate=" + createDate + ", displayInfo=" + displayInfo
			+ ", displayInfoId=" + displayInfoId + ", modifydate=" + modifydate + ", productId=" + productId
			+ ", reservationDate=" + reservationDate + ", reservationEmail=" + reservationEmail + ", reservationInfoId="
			+ reservationInfoId + ", reservationName=" + reservationName + ", reservationTelephone="
			+ reservationTelephone + ", totalPrice=" + totalPrice + "]";
	}
}
