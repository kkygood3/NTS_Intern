/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.constant;

/**
 * 가격 타입
 * 기타 다른 유형이 있다면 아래와 겹치지 않게 1자로 정의하여 기입
 * 성인(A), 청소년(Y), 유아(B), 셋트(S), 장애인(D), 지역주민(C), 어얼리버드(E) 
 * VIP(V), R석(R)
 * <!-- 성인(만 19~64세) 5,000원 / 청소년(만 13~18세) 4,000원<br> 어린이(만 4~12세) 3,000원 / 20인 이상 단체 20% 할인<br> 국가유공자, 장애인, 65세 이상 4,000원 -->
 * @author jinwoo.bae
 */
public enum PriceType {
	A("성인(만 19~64세)"),
	Y("청소년(만 13~18세)"),
	B("어린이(만 4~12세)"),
	S("단체"),
	D("장애인"),
	C("지역주민"),
	E("어얼리버드"),
	V("VIP석"),
	R("ROYAL석");

	private String description;

	private PriceType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
