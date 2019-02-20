package com.nts.reservation.property;

public enum ProductPriceType {
	// 성인(A), 청소년(Y), 유아(B), 셋트(S), 장애인(D), 지역주민(C), 어얼리버드(E), VIP(V), R석(R), B석(B),
	// S석(S), 평일(D) ..?
	A("성인"), Y("청소년"), B("유아"), S("셋트"), D("장애인"), C("지역주민"), E("얼리버드"), V("VIP"), R("R석");

	private String type;

	private ProductPriceType(String type) {
		this.type = type;
	}

	public String getProductPriceType() {
		return type;
	}
}
