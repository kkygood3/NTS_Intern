/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dto;

import java.text.NumberFormat;

import com.nts.reservation.dto.tag.PriceTag;

/**
 * @desc 웹 상에서 클라이언트들에게 값 정보를 보여주기 위해 ProductPriceDto를 변환시킨 Dto
 * @author 육성렬
 */
public class ProductPriceForClientDto {
	private String name;
	private String price;
	private String description;
	private Long priceId;
	private static final NumberFormat numberFormat = NumberFormat.getInstance();

	public ProductPriceForClientDto(ProductPriceDto productPriceDto) {
		this.name = PriceTag.valueOf(productPriceDto.getPriceTypeName()).getName();
		this.price = numberFormat.format(productPriceDto.getPrice());
		this.priceId = productPriceDto.getProductPriceId();
		if (productPriceDto.getDiscountRate() == 0) {
			description = "";
		} else {
			description = this.price + "원" + " (" + (int)productPriceDto.getDiscountRate()
				+ "% 할인가)";
		}
	}

	public String getName() {
		return name;
	}

	public String getPrice() {
		return price;
	}

	public String getDescription() {
		return description;
	}

	public Long getPriceId() {
		return priceId;
	}

	@Override
	public String toString() {
		return "ProductPriceForClientDto [name=" + name + ", price=" + price + ", description=" + description
			+ ", priceId=" + priceId + "]";
	}
}
