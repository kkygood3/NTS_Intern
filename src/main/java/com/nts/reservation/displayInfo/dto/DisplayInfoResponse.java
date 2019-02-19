/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.displayInfo.dto;

import java.util.List;

import com.nts.reservation.comment.dto.Comment;
import com.nts.reservation.product.dto.ProductImage;
import com.nts.reservation.product.dto.ProductPrice;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public class DisplayInfoResponse {
	private double averageScore;
	private List<Comment> comments;
	private DisplayInfo displayInfo;
	private String displayInfoImage;
	private List<ProductImage> productImages;
	private List<ProductPrice> productPrices;

	public double getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(double averageScore) {
		this.averageScore = averageScore;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public DisplayInfo getDisplayInfo() {
		return displayInfo;
	}

	public void setDisplayInfo(DisplayInfo displayInfo) {
		this.displayInfo = displayInfo;
	}

	public String getDisplayInfoImage() {
		return displayInfoImage;
	}

	public void setDisplayInfoImage(String displayInfoImage) {
		this.displayInfoImage = displayInfoImage;
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

	@Override
	public String toString() {
		return "DisplayInfoResponse [averageScore=" + averageScore + ", comments=" + comments + ", displayInfo="
			+ displayInfo + ", displayInfoImage=" + displayInfoImage + ", productImages=" + productImages + "]";
	}
}
