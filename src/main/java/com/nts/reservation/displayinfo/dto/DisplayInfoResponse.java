/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.displayinfo.dto;

import java.util.List;

import com.nts.reservation.comment.dto.Comment;
import com.nts.reservation.product.dto.ProductImage;
import com.nts.reservation.product.dto.ProductPrice;

public class DisplayInfoResponse {
	private double averageScore;
	private List<Comment> comments;
	private List<DisplayInfo> displayInfo;
	private List<DisplayInfoImage> displayInfoImage;
	private List<ProductImage> productImages;
	private List<ProductPrice> producdPrices;

	public double getAverageScore() {
		return averageScore;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public List<DisplayInfo> getDisplayInfo() {
		return displayInfo;
	}

	public List<DisplayInfoImage> getDisplayInfoImage() {
		return displayInfoImage;
	}

	public List<ProductImage> getProductImages() {
		return productImages;
	}

	public List<ProductPrice> getProducdPrices() {
		return producdPrices;
	}

	public void setAverageScore(double averageScore) {
		this.averageScore = averageScore;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public void setDisplayInfo(List<DisplayInfo> displayInfo) {
		this.displayInfo = displayInfo;
	}

	public void setDisplayInfoImage(List<DisplayInfoImage> displayInfoImage) {
		this.displayInfoImage = displayInfoImage;
	}

	public void setProductImages(List<ProductImage> productImages) {
		this.productImages = productImages;
	}

	public void setProducdPrices(List<ProductPrice> producdPrices) {
		this.producdPrices = producdPrices;
	}

	@Override
	public String toString() {
		return "DisplayInfoResponse [averageScore=" + averageScore + ", comments=" + comments + ", displayInfo="
			+ displayInfo + ", displayInfoImage=" + displayInfoImage + ", productImages=" + productImages
			+ ", producdPrices=" + producdPrices + "]";
	}
}
