package com.nts.reservation.dto;

public class Product {
	private Long displayInfoId;
	private String placeName;
	private String productContent;
	private String productDescription;
	private Long productId;
	private String productImageUrl;

	@Override
	public String toString() {
		return "Product [displayInfoId=" + displayInfoId + ", placeName=" + placeName + ", productContent="
			+ productContent + ", productDescription=" + productDescription + ", productId=" + productId
			+ ", productImageUrl=" + productImageUrl + "]";
	}

	public Long getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(Long displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getProductContent() {
		return productContent;
	}

	public void setProductContent(String productContent) {
		this.productContent = productContent;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductImageUrl() {
		return productImageUrl;
	}

	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

}
