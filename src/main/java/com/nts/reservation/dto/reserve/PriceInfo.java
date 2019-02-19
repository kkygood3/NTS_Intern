/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto.reserve;

import com.nts.reservation.property.PriceType;

/**
 * api/reservation 요청에서
 * javascript객체 PriceInfo에 매핑
 */
public class PriceInfo {
	private PriceType type;
	private Integer count;
	
	public PriceType getType() {
		return type;
	}
	public void setType(PriceType type) {
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
		return "PriceInfo [type=" + type + ", count=" + count + "]";
	}
	
}
