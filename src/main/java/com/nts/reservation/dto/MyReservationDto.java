/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto;

/**
 * 나의 예약정보 DTO
 * @author jinwoo.bae
 */
public class MyReservationDto {
	private int id;
	private int productId;
	private int displayInfoId;
	private String categoryName;
	private String productDescription;
	private String reservationDate;
	private String placeName;
	private Boolean cancelFlag;
	private int totalPrice;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String cetegoryName) {
		this.categoryName = cetegoryName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public Boolean getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(Boolean cancelFlag) {
		this.cancelFlag = cancelFlag;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "MyReservationDto [id=" + id + ", productId=" + productId + ", displayInfoId=" + displayInfoId
			+ ", categoryName=" + categoryName + ", productDescription=" + productDescription + ", reservationDate="
			+ reservationDate + ", placeName=" + placeName + ", cancelFlag=" + cancelFlag + ", totalPrice=" + totalPrice
			+ "]";
	}

}
