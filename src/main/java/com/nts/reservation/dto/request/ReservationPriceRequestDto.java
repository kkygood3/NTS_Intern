/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author 육성렬
 */
public class ReservationPriceRequestDto {

	@NotNull
	@Min(1)
	private Integer count;

	@NotNull
	@Min(0)
	private Long productPriceId;

	private Long reservationInfoId;

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

	@Override
	public String toString() {
		return "ReservationPriceRequestDto [count=" + count + ", productPriceId=" + productPriceId
			+ ", reservationInfoId=" + reservationInfoId + "]";
	}

}
