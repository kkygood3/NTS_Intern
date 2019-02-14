/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dto.request;

import static com.nts.reservation.dto.request.regex.RegexPattern.EMAIL_REGEX;
import static com.nts.reservation.dto.request.regex.RegexPattern.PHONE_NUMBER_REGEX;
import static com.nts.reservation.dto.request.regex.RegexPattern.RESERVER_NAME_REGEX;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author 육성렬
 */
public class ReservationRequestDto {

	@NotNull
	@Min(0)
	private Long displayInfoId;

	@NotNull
	@Min(0)
	private Long productId;

	@Pattern(regexp = EMAIL_REGEX)
	private String reservationEmail;

	@Pattern(regexp = RESERVER_NAME_REGEX)
	private String reservationName;

	@Pattern(regexp = PHONE_NUMBER_REGEX)
	private String reservationTel;

	@Future
	private Date reservationDate;

	@Valid
	@NotNull
	@Size(min = 1)
	private List<ReservationPriceRequestDto> prices;

	public Long getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(Long displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getReservationEmail() {
		return reservationEmail;
	}

	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
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

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	public List<ReservationPriceRequestDto> getPrices() {
		return prices;
	}

	public void setPrices(List<ReservationPriceRequestDto> prices) {
		this.prices = prices;
	}

	@Override
	public String toString() {
		return "ReservationRequestDto [displayInfoId=" + displayInfoId + ", productId=" + productId
			+ ", reservationEmail=" + reservationEmail + ", reservationName=" + reservationName + ", reservationTel="
			+ reservationTel + ", reservationDate=" + reservationDate + ", prices=" + prices + "]";
	}

}
