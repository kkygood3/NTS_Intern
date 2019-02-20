package com.nts.reservation.dto.response;

import java.util.List;

import com.nts.reservation.dto.DisplayInfoDto;
import com.nts.reservation.dto.ProductImageDto;
import com.nts.reservation.dto.ProductPriceDto;

public class ReservationResponseDto {
	private DisplayInfoDto displayInfo;
	private ProductImageDto productImage;
	private List<ProductPriceDto> productPriceList;

	public ReservationResponseDto(DisplayInfoDto displayInfo, ProductImageDto productImage,
		List<ProductPriceDto> productPriceList) {
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

	public List<ProductPriceDto> getProductPriceList() {
		return productPriceList;
	}

	@Override
	public String toString() {
		return "ReservationResponseDto [displayInfo=" + displayInfo + ", productImage=" + productImage
			+ ", productPrices=" + productPriceList + "]";
	}

}
