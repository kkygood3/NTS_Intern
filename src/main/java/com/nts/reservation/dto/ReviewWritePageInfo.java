package com.nts.reservation.dto;

public class ReviewWritePageInfo {
	private int productId;
	private String description;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ReviewWritePageInfo [productId=" + productId + ", description=" + description + "]";
	}
}
