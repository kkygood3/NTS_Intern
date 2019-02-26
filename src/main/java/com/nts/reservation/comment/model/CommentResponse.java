/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.comment.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.nts.reservation.common.model.Response;

public class CommentResponse extends Response {

	private CommentListInfo commentListInfo;

	public CommentResponse() {}

	public CommentResponse(CommentListInfo commentListInfo) {
		this.commentListInfo = commentListInfo;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public CommentListInfo getCommentListInfo() {
		return commentListInfo;
	}

	public void setCommentListInfo(CommentListInfo commentListInfo) {
		this.commentListInfo = commentListInfo;
	}

}
