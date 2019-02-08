package com.nts.reservation.dto.detail;

import java.util.List;

public class DetailDisplay {
	private double averageScore;
	private DisplayInfo displayInfo;
	private List<DisplayInfoImage> displayInfoImages;
	private List<ProductImage> productImages;
	private List<ProductPrice> productPrices;

	public double getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(double averageScore) {
		this.averageScore = averageScore;
	}

	public DisplayInfo getDisplayInfo() {
		return displayInfo;
	}

	public void setDisplayInfo(DisplayInfo displayInfo) {
		this.displayInfo = displayInfo;
	}

	public List<DisplayInfoImage> getDisplayInfoImages() {
		return displayInfoImages;
	}

	public void setDisplayInfoImages(List<DisplayInfoImage> displayInfoImages) {
		this.displayInfoImages = displayInfoImages;
	}

	public List<ProductImage> getProductImages() {
		return productImages;
	}

	public void setProductImages(List<ProductImage> productImages) {
		this.productImages = productImages;
	}

	public List<ProductPrice> getProductPrices() {
		return productPrices;
	}

	public void setProductPrices(List<ProductPrice> productPrices) {
		this.productPrices = productPrices;
	}
}
