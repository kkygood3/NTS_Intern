/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto.reserve;

/**
 * api/reservation 요청에서
 * javascript객체 PriceInfo에 매핑
 */
public class ReservePriceInfo {
	private ReservePriceType type;
	private Integer count;

	public ReservePriceType getType() {
		return type;
	}

	public void setType(ReservePriceType type) {
		this.type = type;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "ReservePriceInfo [type=" + type + ", count=" + count + "]";
	}
}
