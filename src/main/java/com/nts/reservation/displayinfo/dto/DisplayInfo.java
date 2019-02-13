/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.displayinfo.dto;

public class DisplayInfo {
	private Integer CategoryId;
	private String CategoryName;
	private String createDate;
	private Integer displayInfoId;
	private String email;
	private String homepage;
	private String modifyDate;
	private String openingHours;
	private String placeLot;
	private String placeName;
	private String placeStreet;
	private String productContent;
	private String productDescription;
	private String productEvent;
	private Integer productId;
	private String telephone;

	public Integer getCategoryId() {
		return CategoryId;
	}

	public String getCategoryName() {
		return CategoryName;
	}

	public String getCreateDate() {
		return createDate;
	}

	public Integer getDisplayInfoId() {
		return displayInfoId;
	}

	public String getEmail() {
		return email;
	}

	public String getHomepage() {
		return homepage;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public String getOpeningHours() {
		return openingHours;
	}

	public String getPlaceLot() {
		return placeLot;
	}

	public String getPlaceName() {
		return placeName;
	}

	public String getPlaceStreet() {
		return placeStreet;
	}

	public String getProductContent() {
		return productContent;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public String getProductEvent() {
		return productEvent;
	}

	public Integer getProductId() {
		return productId;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setCategoryId(Integer categoryId) {
		CategoryId = categoryId;
	}

	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public void setDisplayInfoId(Integer displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public void setOpeningHours(String openingHours) {
		this.openingHours = openingHours;
	}

	public void setPlaceLot(String placeLot) {
		this.placeLot = placeLot;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public void setPlaceStreet(String placeStreet) {
		this.placeStreet = placeStreet;
	}

	public void setProductContent(String productContent) {
		this.productContent = productContent;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public void setProductEvent(String productEvent) {
		this.productEvent = productEvent;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Override
	public String toString() {
		return "DisplayInfo [CategoryId=" + CategoryId + ", CategoryName=" + CategoryName + ", createDate=" + createDate
			+ ", displayInfoId=" + displayInfoId + ", email=" + email + ", homepage=" + homepage + ", modifyDate="
			+ modifyDate + ", openingHours=" + openingHours + ", placeLot=" + placeLot + ", placeName=" + placeName
			+ ", placeStreet=" + placeStreet + ", productContent=" + productContent + ", productDescription="
			+ productDescription + ", productEvent=" + productEvent + ", productId=" + productId + ", telephone="
			+ telephone + "]";
	}
}
