/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto.reserve;

/**
 * 상품마다 가지는 타입과 타입에 해당하는 이름을 정의.
 * ex)PriceType.A(Adult)-> 성인
 */
public enum ReservePriceType {
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

	private ReservePriceType(String typeLabel) {
		this.typeLabel = typeLabel;
	}

	public String getTypeLabel() {
		return typeLabel;
	}
}
