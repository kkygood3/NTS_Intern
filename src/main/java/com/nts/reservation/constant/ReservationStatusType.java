/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.constant;

/**
 * 예약 상태 Type
 * 이용예정(TODO), 이용완료(DONE), 취소,환불(CANCLE)
 * @author jinwoo.bae
 */
public enum ReservationStatusType {
	TODO("이용예정"),
	DONE("이용완료"),
	CANCLE("취소,환불");

	private final String description;

	private ReservationStatusType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
