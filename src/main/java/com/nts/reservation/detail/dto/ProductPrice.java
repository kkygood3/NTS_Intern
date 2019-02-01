package com.nts.reservation.detail.dto;

import java.time.LocalDateTime;

public class ProductPrice {
	private Long productPriceId;
	private Long productId;
	private String priceTypeName;
	private int price;
	private int discountRate;
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

	public void setPrice(int price) {
		this.price = price;
	}

	public int getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(int discountRate) {
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
