/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto.response;

import java.util.List;

import com.nts.reservation.dto.DisplayInfoDto;
import com.nts.reservation.dto.ProductPriceDto;
import com.nts.reservation.util.DateUtil;

/**
 * 예약페이지 response용 DTO
 * @author jinwoo.bae
 */
public class ReserveResponseDto {
	private DisplayInfoDto displayInfo; // 전시상품 DTO
	private List<ProductPriceDto> productPrices; // 상품 가격 리스트
	private String productImageUrl;
	private double minPrice;
	private String reservationDate; // 예매일생성 규칙 : 예매일 기준  오늘포함해서 1-5일 랜덤값으로 서버에서 생성해서 내려줌

	public ReserveResponseDto(DisplayInfoDto displayInfo, List<ProductPriceDto> productPrices, String productImageUrl) {
		setDisplayInfo(displayInfo);
		setProductPrices(productPrices);
		setProductImageUrl(productImageUrl);
		setMinPrice();
		setReservationDate();
	}

	public DisplayInfoDto getDisplayInfo() {
		return displayInfo;
	}

	public List<ProductPriceDto> getProductPrices() {
		return productPrices;
	}

	public void setProductPrices(List<ProductPriceDto> productPrices) {
		this.productPrices = productPrices;
	}

	public void setDisplayInfo(DisplayInfoDto displayInfo) {
		this.displayInfo = displayInfo;
	}

	public String getProductImageUrl() {
		return productImageUrl;
	}

	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}

	public double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice() {
		if (productPrices == null || productPrices.size() == 0) {
			this.minPrice = 0;
		} else {
			this.minPrice = productPrices.stream()
				.mapToDouble((v) -> v.getPrice() * (100 - v.getDiscountRate()) / 100).min().getAsDouble();
		}
	}

	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate() {
		reservationDate = DateUtil.getTodayWithPlusRandomDays(1, 5);
	}

}
