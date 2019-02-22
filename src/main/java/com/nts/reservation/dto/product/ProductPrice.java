package com.nts.reservation.dto.product;
/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 */

import java.time.LocalDateTime;

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

public class ProductPrice {
	private Long productPriceId;
	private Long productId;
	private String priceTypeName;
	private Integer price;
	private Integer discountRate;
	private LocalDateTime createDate;
	private LocalDateTime modifyDate;

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

	public void setPrice(Integer price) {
		this.price = price;
	}

	public int getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(Integer discountRate) {
		this.discountRate = discountRate;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(LocalDateTime modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "ProductPrices [productPriceId=" + productPriceId + ", productId=" + productId + ", priceTypeName="
			+ priceTypeName + ", price=" + price + ", discountRate=" + discountRate + ", createDate=" + createDate
			+ ", modifyDate=" + modifyDate + "]";
	}

}
