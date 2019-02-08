/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.resevation.dto;

import java.util.List;

/**
 * 전시상품 Response용 DTO
 * @author jinwoo.bae
 */
public class DisplayInfoResponseDto {
	DisplayInfoDto displayInfo;
	List<ProductImageDto> productImages;
	DisplayInfoImageDto displayInfoImage;
	List<CommentDto> comments;
	double averageScore;
	List<ProductPriceDto> productPrices;

	public DisplayInfoDto getDisplayInfo() {
		return displayInfo;
	}

	public void setDisplayInfo(DisplayInfoDto displayInfo) {
		this.displayInfo = displayInfo;
	}

	public List<ProductImageDto> getProductImages() {
		return productImages;
	}

	public void setProductImages(List<ProductImageDto> productImages) {
		this.productImages = productImages;
	}

	public DisplayInfoImageDto getDisplayInfoImage() {
		return displayInfoImage;
	}

	public void setDisplayInfoImage(DisplayInfoImageDto displayInfoImage) {
		this.displayInfoImage = displayInfoImage;
	}

	public List<CommentDto> getComments() {
		return comments;
	}

	public void setComments(List<CommentDto> comments) {
		this.comments = comments;
	}

	public double getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(double averageScore) {
		this.averageScore = averageScore;
	}

	public List<ProductPriceDto> getProductPrices() {
		return productPrices;
	}

	public void setProductPrices(List<ProductPriceDto> productPrices) {
		this.productPrices = productPrices;
	}

}
