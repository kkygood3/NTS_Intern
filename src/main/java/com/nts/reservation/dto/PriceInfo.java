package com.nts.reservation.dto;

import com.nts.reservation.property.ProductPriceType;

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
		return priceTypeName;
	}

	public void setPriceTypeName(ProductPriceType priceTypeName) {
		this.priceTypeName = priceTypeName.getProductPriceType();
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
