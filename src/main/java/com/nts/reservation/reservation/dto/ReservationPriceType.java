/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.reservation.dto;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public enum ReservationPriceType {
	성인("A"),
	유아("B"),
	지역주민("C"),
	장애인("D"),
	얼리버드("E"),
	R석("R"),
	셋트("S"),
	VIP("V"),
	청소년("Y");

	private String typeLabel;

	private ReservationPriceType(String typeLabel) {
		this.typeLabel = typeLabel;
	}

	public String getLabel() {
		return typeLabel;
	}
}
