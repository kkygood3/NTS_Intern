package com.nts.reservation.dto.reservation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 * 
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Price {
	private Integer count;
	private Long productPriceId;
	private Long reservationInfoId;
	private Long reservationInfoPriceId;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Long getProductPriceId() {
		return productPriceId;
	}

	public void setProductPriceId(Long productPriceId) {
		this.productPriceId = productPriceId;
	}

	public Long getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(Long reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public Long getReservationInfoPriceId() {
		return reservationInfoPriceId;
	}

	public void setReservationInfoPriceId(Long reservationInfoPriceId) {
		this.reservationInfoPriceId = reservationInfoPriceId;
	}

	@Override
	public String toString() {
		return "Price [count=" + count + ", productPriceId=" + productPriceId + ", reservationInfoId="
			+ reservationInfoId + ", reservationInfoPriceId=" + reservationInfoPriceId + "]";
	}
}
