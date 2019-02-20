/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dto.response;

import java.util.List;

import com.nts.reservation.dto.CommentDto;
import com.nts.reservation.dto.DisplayInfoDto;
import com.nts.reservation.dto.DisplayInfoImageDto;
import com.nts.reservation.dto.ProductImageDto;
import com.nts.reservation.dto.ProductPriceDto;

/**
 * @author 육성렬
 */
public class DisplayInfoResponseDto {
	private float averageScore;
	private List<CommentDto> comments;
	private DisplayInfoDto displayInfo;
	private DisplayInfoImageDto displayInfoImage;
	private List<ProductImageDto> productImages;
	private List<ProductPriceDto> productPrices;

	public DisplayInfoResponseDto(float averageScore, List<CommentDto> comments, DisplayInfoDto displayInfo,
		DisplayInfoImageDto displayInfoImage, List<ProductImageDto> productImages,
		List<ProductPriceDto> productPrices) {
		this.averageScore = averageScore;
		this.comments = comments;
		this.displayInfo = displayInfo;
		this.displayInfoImage = displayInfoImage;
		this.productImages = productImages;
		this.productPrices = productPrices;
	}

	public float getAverageScore() {
		return averageScore;
	}

	public List<CommentDto> getComments() {
		return comments;
	}

	public DisplayInfoDto getDisplayInfo() {
		return displayInfo;
	}

	public DisplayInfoImageDto getDisplayInfoImage() {
		return displayInfoImage;
	}

	public List<ProductImageDto> getProductImages() {
		return productImages;
	}

	public List<ProductPriceDto> getProductPrices() {
		return productPrices;
	}

	@Override
	public String toString() {
		return "DisplayInfoResponseDto [averageScore=" + averageScore + ", comments=" + comments + ", displayInfo="
			+ displayInfo + ", displayInfoImage=" + displayInfoImage + ", productImages=" + productImages
			+ ", productPrices=" + productPrices + "]";
	}

}
