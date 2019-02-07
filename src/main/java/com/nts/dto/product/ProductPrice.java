/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.dto.product;

/**
 * @author 전연빈
 */
public class ProductPrice {
	private String createDate;			// 생성일
	private double discountRate;		// 할인율
	private String modifiyDate;			// 수정일
	private int price;					// 가격
	private String priceTypeName;		// 가격 타입명
	private int productId;				// 상품 Id
	private int productPriceId;			// 상품 가격 Id

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public double getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}

	public String getModfiyDate() {
		return modifiyDate;
	}

	public void setModfiyDate(String modfiyDate) {
		this.modifiyDate = modfiyDate;
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

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getProductPriceId() {
		return productPriceId;
	}

	public void setProductPriceId(int productPriceId) {
		this.productPriceId = productPriceId;
	}

}
