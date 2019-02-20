/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto;

import com.nts.reservation.dto.primitive.ReservationInfoDto;

/**
 * 나의 예약정보 DTO
 * @author jinwoo.bae
 */
public class ReservationDisplayInfoDto extends ReservationInfoDto {
	// product table - 상품명
	private String productDescription;
	// category table - 카테고리명
	private String categoryName;
	// displayInfo table - 장소명
	private String placeName;
	// reservationInfoPrice table - Total price
	private int totalPrice;

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

}
