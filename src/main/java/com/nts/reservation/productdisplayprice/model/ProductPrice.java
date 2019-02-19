/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.productdisplayprice.model;

public class ProductPrice {

	private int productPriceId;
	private PriceType priceType;
	private int price;
	private int discountRate;

	@Override
	public String toString() {
		return "ProductPrice [productPriceId=" + productPriceId + ", priceType=" + priceType + ", price=" + price
			+ ", discountRate=" + discountRate + "]";
	}

	public int getProductPriceId() {
		return productPriceId;
	}

	public void setProductPriceId(int productPriceId) {
		this.productPriceId = productPriceId;
	}

	public PriceType getPriceType() {
		return priceType;
	}

	public void setPriceType(PriceType priceType) {
		this.priceType = priceType;
	}

	public void setPriceType(String priceTypeName) {
		this.priceType = PriceType.valueOf(priceTypeName);
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(int discountRate) {
		this.discountRate = discountRate;
	}

}
