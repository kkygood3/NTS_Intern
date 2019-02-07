/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.model;

public class ProductDisplayResponse {

	private ProductDisplay productDisplay;
	private CommentsInfo commentsInfo;

	public ProductDisplayResponse() {}

	public ProductDisplayResponse(ProductDisplay productDisplay, CommentsInfo commentsInfo) {
		this.productDisplay = productDisplay;
		this.commentsInfo = commentsInfo;
	}

	@Override
	public String toString() {
		return "ProductDisplayResponse [productDisplay=" + productDisplay + ", commentsInfo=" + commentsInfo + "]";
	}

	public ProductDisplay getProductDisplay() {
		return productDisplay;
	}

	public void setProductDisplay(ProductDisplay productDisplay) {
		this.productDisplay = productDisplay;
	}

	public CommentsInfo getCommentsInfo() {
		return commentsInfo;
	}

	public void setCommentsInfo(CommentsInfo commentsInfo) {
		this.commentsInfo = commentsInfo;
	}

}
