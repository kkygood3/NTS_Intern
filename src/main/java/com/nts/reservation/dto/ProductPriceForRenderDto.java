/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dto;

/**
 * @desc 웹 상에서 클라이언트들에게 값 정보를 보여주기 위해 ProductPriceDto를 변환시킨 Dto
 * @author 육성렬
 */
public class ProductPriceForRenderDto {
	private String name;
	private String price;
	private String description;
	private Long priceId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPriceId() {
		return priceId;
	}

	public void setPriceId(Long priceId) {
		this.priceId = priceId;
	}

	@Override
	public String toString() {
		return "ProductPriceForRenderDto [name=" + name + ", price=" + price + ", description=" + description
			+ ", priceId=" + priceId + "]";
	}

}
