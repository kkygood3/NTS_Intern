/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.resevation.dto;

/**
 * 상품 가격 모델
 * @author jinwoo.bae
 */
public class ProductPriceDto {
	int productPriceId;
	int productId;
	/**
	 * 가격 타입명 변수
	 * 성인(A), 청소년(Y), 유아(B), 셋트(S), 장애인(D), 지역주민(C), 어얼리버드(E) 
	 * 기타 다른 유형이 있다면 위와 겹치지 않게 1자로 정의하여 기입 
	 */
	String priceTypeName;
	int price; // 상품 가격
	double discountRate; // 할인율
	String createDate;
	String modifyDate;

	public int getProductPriceId() {
		return productPriceId;
	}

	public void setProductPriceId(int productPriceId) {
		this.productPriceId = productPriceId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
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

	public double getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(double discountRate) {
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

}
