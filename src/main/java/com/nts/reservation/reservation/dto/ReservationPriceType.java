/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.reservation.dto;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public enum ReservationPriceType {
	A("성인"),
	B("유아"),
	C("지역주민"),
	D("장애인"),
	E("얼리버드"),
	R("R석"),
	S("셋트"),
	V("VIP"),
	Y("청소년");

	private String typeLabel;

	private ReservationPriceType(String typeLabel) {
		this.typeLabel = typeLabel;
	}

	public String getLabel() {
		return typeLabel;
	}
}
