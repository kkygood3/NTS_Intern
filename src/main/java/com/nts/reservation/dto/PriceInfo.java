package com.nts.reservation.dto;

public class PriceInfo {
	private long productPriceId;
	private String priceTypeName;
	private long price;
	private double discountRate;

	public long getProductPriceId() {
		return productPriceId;
	}

	public void setProductPriceId(long productPriceId) {
		this.productPriceId = productPriceId;
	}

	public String getPriceTypeName() {
		// 성인(A), 청소년(Y), 유아(B), 셋트(S), 장애인(D), 지역주민(C), 어얼리버드(E), VIP(V), R석(R), B석(B),
		// S석(S), 평일(D)
		switch (priceTypeName) {
		case "A":
			return "성인";
		case "Y":
			return "청소년";
		case "B":
			return "유아";
		case "S":
			return "셋트";
		case "D":
			return "장애인";
		case "C":
			return "지역주민";
		case "E":
			return "얼리버드";
		case "V":
			return "VIP";
		case "R":
			return "R석";
//			case "B":
//				return "성인";
//			case "S":
//				return "성인";
//			case "D":
//				return "성인";
		default:
			return "";
		}
	}

	public void setPriceTypeName(String priceTypeName) {
		this.priceTypeName = priceTypeName;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public double getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}

	@Override
	public String toString() {
		return "PriceInfo [productPriceId=" + productPriceId + ", priceTypeName=" + priceTypeName + ", price=" + price
				+ ", discountRate=" + discountRate + "]";
	}
}
