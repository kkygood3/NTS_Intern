/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dto.tag;

/**
 * @author 육성렬
 */
public enum PriceTag {

	A("A", "성인"),
	Y("Y", "청년"),
	S("S", "세트"),
	D("D", "장애인"),
	C("C", "지역주민"),
	E("E", "얼리버드"),
	V("V", "VIP"),
	R("R", "R석"),
	B("B", "유아");

	private String type;
	private String name;

	PriceTag(String type, String name) {
		this.type = type;
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}
