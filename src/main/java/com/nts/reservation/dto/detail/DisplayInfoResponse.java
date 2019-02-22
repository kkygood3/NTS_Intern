package com.nts.reservation.dto.detail;

/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 */
import java.util.List;

import com.nts.reservation.dto.comment.Comment;
import com.nts.reservation.dto.product.ProductImage;
import com.nts.reservation.dto.product.ProductPrice;

/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 * 
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 *
 */

public class DisplayInfoResponse {

	private DisplayInfo displayInfo;
	private List<ProductImage> productImages;
	private DisplayInfoImage displayInfoImage;
	private List<Comment> comments;
	private Double averageScore;
	private List<ProductPrice> productPrices;

	public DisplayInfo getDisplayInfo() {
		return displayInfo;
	}

	public void setDisplayInfo(DisplayInfo displayInfo) {
		this.displayInfo = displayInfo;
	}

	public List<ProductImage> getProductImages() {
		return productImages;
	}

	public void setProductImages(List<ProductImage> productImages) {
		this.productImages = productImages;
	}

	public DisplayInfoImage getDisplayInfoImage() {
		return displayInfoImage;
	}

	public void setDisplayInfoImage(DisplayInfoImage displayInfoImage) {
		this.displayInfoImage = displayInfoImage;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Double getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(Double averageScore) {
		this.averageScore = averageScore;
	}

	public List<ProductPrice> getProductPrices() {
		return productPrices;
	}

	public void setProductPrices(List<ProductPrice> productPrices) {
		this.productPrices = productPrices;
	}

	public DisplayInfoResponse(Builder builder) {
		displayInfo = builder.displayInfo;
		productImages = builder.productImages;
		displayInfoImage = builder.displayInfoImage;
		comments = builder.comments;
		averageScore = builder.averageScore;
		productPrices = builder.productPrices;
	}

	public static final class Builder {
		private DisplayInfo displayInfo;
		private List<ProductImage> productImages;
		private DisplayInfoImage displayInfoImage;
		private List<Comment> comments;
		private Double averageScore;
		private List<ProductPrice> productPrices;

		public Builder displayInfo(DisplayInfo displayInfo) {
			this.displayInfo = displayInfo;
			return this;
		}

		public Builder productImages(List<ProductImage> productImages) {
			this.productImages = productImages;
			return this;
		}

		public Builder displayInfoImage(DisplayInfoImage displayInfoImage) {
			this.displayInfoImage = displayInfoImage;
			return this;
		}

		public Builder comments(List<Comment> comments) {
			this.comments = comments;
			return this;
		}

		public Builder averageScore(Double averageScore) {
			this.averageScore = averageScore;
			return this;
		}

		public Builder productPrices(List<ProductPrice> productPrices) {
			this.productPrices = productPrices;
			return this;
		}

		public DisplayInfoResponse build() {
			return new DisplayInfoResponse(this);
		}
	}
}
