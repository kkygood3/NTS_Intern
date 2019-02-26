/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.productdisplay.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.nts.reservation.comment.model.CommentListInfo;
import com.nts.reservation.common.model.Response;

public class ProductDisplayResponse extends Response {

	private ProductDisplay productDisplay;
	private CommentListInfo commentListInfo;

	public ProductDisplayResponse() {}

	public ProductDisplayResponse(ProductDisplay productDisplay, CommentListInfo commentListInfo) {
		this.productDisplay = productDisplay;
		this.commentListInfo = commentListInfo;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public ProductDisplay getProductDisplay() {
		return productDisplay;
	}

	public void setProductDisplay(ProductDisplay productDisplay) {
		this.productDisplay = productDisplay;
	}

	public CommentListInfo getCommentListInfo() {
		return commentListInfo;
	}

	public void setCommentListInfo(CommentListInfo commentListInfo) {
		this.commentListInfo = commentListInfo;
	}

}
