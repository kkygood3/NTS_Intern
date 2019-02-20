/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto.param;

import java.util.List;

import com.nts.reservation.constant.RegularExpression;
import com.nts.reservation.dto.ReservationInfoPriceDto;
import com.nts.reservation.util.RegexValidator;

/**
 * 예약하기 파라미터용 DTO
 * @author jinwoo.bae
 */
public class ReservationParamDto {
	private int displayInfoId;
	private int productId;
	private List<ReservationInfoPriceDto> prices;
	private String reservationName;
	private String reservationTelephone;
	private String reservationEmail;
	private String reservationDate;

	public int getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public List<ReservationInfoPriceDto> getPrices() {
		return prices;
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

	public String getReservationEmail() {
		return reservationEmail;
	}

	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}

	@Override
	public String toString() {
		return "ReservationParamDto [displayInfoId=" + displayInfoId + ", productId=" + productId + ", prices=" + prices
			+ ", reservationName=" + reservationName + ", reservationTelephone=" + reservationTelephone
			+ ", reservationEmail=" + reservationEmail + ", reservationDate=" + reservationDate + "]";
	}

	public boolean isValid() {
		if (!RegexValidator.isValid(RegularExpression.NAME_REGEXP, reservationName)) {
			return false;
		}
		if (!RegexValidator.isValid(RegularExpression.PHONE_REGEXP, reservationTelephone)) {
			return false;
		}
		if (!RegexValidator.isValid(RegularExpression.EMAIL_REGEXP, reservationEmail)) {
			return false;
		}
		return true;
	}

}
