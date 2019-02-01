/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dto;

import java.util.Date;

/**
 * @author 육성렬
 */
public class ProductPriceDto {
	private Date createDate;
	private float discountRate;
	private Date modifyDate;
	private int price;
	private String priceTypeName;
	private Long productId;
	private Long productPriceId;

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public float getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(float discountRate) {
		this.discountRate = discountRate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getPriceTypeName() {
		return priceTypeName;
	}

	public void setPriceTypeName(String priceTypeName) {
		this.priceTypeName = priceTypeName;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getProductPriceId() {
		return productPriceId;
	}

	public void setProductPriceId(Long productPriceId) {
		this.productPriceId = productPriceId;
	}

	@Override
	public String toString() {
		return "ProductPriceDto [createDate=" + createDate + ", discountRate=" + discountRate + ", modifyDate="
			+ modifyDate + ", price=" + price + ", priceTypeName=" + priceTypeName + ", productId=" + productId
			+ ", productPriceId=" + productPriceId + "]";
	}
}
