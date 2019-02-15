package com.nts.reservation.dto;

public class PriceInfo {
	private String priceTypeName;
	private long price;
	private double discountRate;

	public String getPriceTypeName() {
		return priceTypeName;
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
		return "PriceInfo [priceTypeName=" + priceTypeName + ", price=" + price + ", discountRate=" + discountRate
			+ "]";
	}
}
