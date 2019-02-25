/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.productprice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.OBJECT)
public enum PriceType {
	A("성인"),
	Y("청소년"),
	B("유아"),
	S("세트"),
	D("장애인"),
	C("지역주민"),
	E("얼리버드"),
	V("VIP"),
	R("R석");

	private String typeName;

	PriceType(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeName() {
		return this.typeName;
	}

}