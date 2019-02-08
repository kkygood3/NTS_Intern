package com.nts.reservation.detail.dto;
/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 */

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

public class ProductPrice {
	private Long productPriceId;
	private Long productId;
	private String priceTypeName;
	private int price;
	private int discountRate;
	private String createDate;
	private String modifyDate;

	public Long getProductPriceId() {
		return productPriceId;
	}

	public void setProductPriceId(Long productPriceId) {
		this.productPriceId = productPriceId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getPriceTypeName() {
		return priceTypeName;
	}

	public void setPriceTypeName(String priceTypeName) {
		this.priceTypeName = priceTypeName;
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

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "ProductPrices [productPriceId=" + productPriceId + ", productId=" + productId + ", priceTypeName="
			+ priceTypeName + ", price=" + price + ", discountRate=" + discountRate + ", createDate=" + createDate
			+ ", modifyDate=" + modifyDate + "]";
	}

}
