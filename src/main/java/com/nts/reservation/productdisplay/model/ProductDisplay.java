/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.productdisplay.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ProductDisplay {

	private int productId;
	private int displayInfoId;
	private String productDescription;
	private String productContent;
	private List<String> productDisplayImageUrlList;
	private String placeName;
	private String placeLot;
	private String placeStreet;
	private String telephone;
	private String displayInfoImageUrl;

	public ProductDisplay() {
		productDisplayImageUrlList = new ArrayList<>();
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
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

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductContent() {
		return productContent;
	}

	public void setProductContent(String productContent) {
		this.productContent = productContent;
	}

	public List<String> getProductDisplayImageUrlList() {
		return productDisplayImageUrlList;
	}

	public void setProductDisplayImageUrlList(List<String> productDisplayImageUrlList) {
		this.productDisplayImageUrlList = productDisplayImageUrlList;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getPlaceLot() {
		return placeLot;
	}

	public void setPlaceLot(String placeLot) {
		this.placeLot = placeLot;
	}

	public String getPlaceStreet() {
		return placeStreet;
	}

	public void setPlaceStreet(String placeStreet) {
		this.placeStreet = placeStreet;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getDisplayInfoImageUrl() {
		return displayInfoImageUrl;
	}

	public void setDisplayInfoImageUrl(String displayInfoImageUrl) {
		this.displayInfoImageUrl = displayInfoImageUrl;
	}
}
