package com.nts.reservation.dto.detail;

/**
 * /api/products/{displayInfoId}/extra요청에 추가 이미지를 담는 DTO
 */
public class DetailExtraImage {
	private String productImage;
	private String productDescription;

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

}
