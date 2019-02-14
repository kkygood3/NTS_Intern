package com.nts.reservation.dto.response;

import java.util.List;

import com.nts.reservation.dto.DisplayInfoDto;
import com.nts.reservation.dto.ProductImageDto;
import com.nts.reservation.dto.ProductPriceForRenderDto;

public class ReservationResponseDto {
	private DisplayInfoDto displayInfo;
	private ProductImageDto productImage;
	private List<ProductPriceForRenderDto> productPriceList;

	public ReservationResponseDto(DisplayInfoDto displayInfo, ProductImageDto productImage,
		List<ProductPriceForRenderDto> productPriceList) {
		this.displayInfo = displayInfo;
		this.productImage = productImage;
		this.productPriceList = productPriceList;
	}

	public DisplayInfoDto getDisplayInfo() {
		return displayInfo;
	}

	public ProductImageDto getProductImage() {
		return productImage;
	}

	public List<ProductPriceForRenderDto> getProductPriceList() {
		return productPriceList;
	}

	@Override
	public String toString() {
		return "ReservationResponseDto [displayInfo=" + displayInfo + ", productImage=" + productImage
			+ ", productPrices=" + productPriceList + "]";
	}

}
