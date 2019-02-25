/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto;

import com.nts.reservation.constant.PriceType;

/**
 * 상품 가격 모델
 * @author jinwoo.bae
 */
public class ProductPriceDto {
	int id;
	PriceType type;
	String typeDescription;
	int price; // 상품 가격
	double discountRate; // 할인율

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PriceType getType() {
		return type;
	}

	public void setType(String typeName) {
		this.type = PriceType.valueOf(typeName);
		setTypeDescription(type.getDescription());
	}

	public String getTypeDescription() {
		return typeDescription;
	}

	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
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

}
