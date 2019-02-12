/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto;

import java.util.List;

/**
 * 전시상품 Response용 DTO
 * 
 * @author jinwoo.bae
 */
public class DisplayInfoResponseDto {
	DisplayInfoDto displayInfo;
	DisplayInfoImageDto displayInfoImage;
	List<ProductImageDto> productImages;

	public DisplayInfoResponseDto(DisplayInfoDto displayInfo, DisplayInfoImageDto displayInfoImage,
			List<ProductImageDto> productImages) {
		this.displayInfo = displayInfo;
		this.displayInfoImage = displayInfoImage;
		this.productImages = productImages;
	}

	public DisplayInfoDto getDisplayInfo() {
		return displayInfo;
	}

	public void setDisplayInfo(DisplayInfoDto displayInfo) {
		this.displayInfo = displayInfo;
	}

	public DisplayInfoImageDto getDisplayInfoImage() {
		return displayInfoImage;
	}

	public void setDisplayInfoImage(DisplayInfoImageDto displayInfoImage) {
		this.displayInfoImage = displayInfoImage;
	}

	public List<ProductImageDto> getProductImages() {
		return productImages;
	}

	public void setProductImages(List<ProductImageDto> productImages) {
		this.productImages = productImages;
	}

}
