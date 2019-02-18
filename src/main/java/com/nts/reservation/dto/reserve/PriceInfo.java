package com.nts.reservation.dto.reserve;

/**
 * api/reservation 요청에서
 * javascript객체 PriceInfo에 매핑
 */
public class PriceInfo {
	private String type;
	private Integer count;
	public String getType() {
		return type;
	}
	public void setType(String type) {
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
