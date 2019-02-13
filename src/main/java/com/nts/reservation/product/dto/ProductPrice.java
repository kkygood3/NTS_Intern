/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.product.dto;

public class ProductPrice {
	private String createDate;
	private double discountRate;
	private String modifyDate;
	private Integer price;
	private String priceTypeName;
	private Integer productId;
	private Integer productPriceId;

	public String getCreateDate() {
		return createDate;
	}

	public double getDiscountRate() {
		return discountRate;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public Integer getPrice() {
		return price;
	}

	public String getPriceTypeName() {
		return priceTypeName;
	}

	public Integer getProductId() {
		return productId;
	}

	public Integer getProductPriceId() {
		return productPriceId;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public void setPriceTypeName(String priceTypeName) {
		this.priceTypeName = priceTypeName;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public void setProductPriceId(Integer productPriceId) {
		this.productPriceId = productPriceId;
	}

	@Override
	public String toString() {
		return "ProductPrice [createDate=" + createDate + ", discountRate=" + discountRate + ", modifyDate="
			+ modifyDate + ", price=" + price + ", priceTypeName=" + priceTypeName + ", productId=" + productId
			+ ", productPriceId=" + productPriceId + "]";
	}
}
