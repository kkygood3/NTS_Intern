/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.reservation.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReservationUpdateParam {
	private int reservationInfoId;
	private String reservationEmail;

	/**
	 * POST 방식으로 들어온 값의 Id와 Email부분을 검증하는 메소드
	 * @param reservationParam
	 */
	public boolean isValid(ReservationUpdateParam updateParam) {
		String emailReg = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";

		if (updateParam.getReservationInfoId() <= 0 || updateParam.getReservationEmail() == null
			|| !updateParam.getReservationEmail().matches(emailReg)) {
			return false;
		}
		return true;
	}
}
