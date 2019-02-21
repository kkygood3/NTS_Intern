/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.reservation.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ReservationParam {
	private Integer displayInfoId;
	private List<ReservationPrice> prices;
	private Integer productId;
	private String reservationEmail;
	private String reservationName;
	private String reservationTelephone;
	private String reservationYearMonthDay;

	/**
	 *
	 * POST 방식으로 들어온 값들을 검증하는 메소드
	 * @param reservationParam
	 */
	public boolean isValid(ReservationParam reservationParam) {
		if(displayInfoId == null || productId == null || prices == null || reservationEmail == null
			|| reservationName == null || reservationTelephone == null) {
			return false;
		}
		return true;
	}
}
