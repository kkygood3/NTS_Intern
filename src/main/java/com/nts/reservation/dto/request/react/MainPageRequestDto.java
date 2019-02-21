package com.nts.reservation.dto.request.react;

import com.nts.reservation.dto.response.CategoryResponseDto;
import com.nts.reservation.dto.response.ProductResponseDto;
import com.nts.reservation.dto.response.PromotionResponseDto;

public class MainPageRequestDto {
	private String userEmail;
	private ProductResponseDto products;
	private PromotionResponseDto promotions;
	private CategoryResponseDto categories;

	public MainPageRequestDto(String userEmail, ProductResponseDto products, PromotionResponseDto promotions,
			CategoryResponseDto categories) {
		this.userEmail = userEmail;
		this.products = products;
		this.promotions = promotions;
		this.categories = categories;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public ProductResponseDto getProducts() {
		return products;
	}

	public PromotionResponseDto getPromotions() {
		return promotions;
	}

	public CategoryResponseDto getCategories() {
		return categories;
	}

	@Override
	public String toString() {
		return "MainPageRequestDto [userEmail=" + userEmail + ", products=" + products + ", promotions=" + promotions
				+ ", categories=" + categories + "]";
	}

}
