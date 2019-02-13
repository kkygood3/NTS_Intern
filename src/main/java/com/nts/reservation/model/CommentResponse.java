/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.model;

public class CommentResponse {
	private CommentListInfo commentListInfo;

	public CommentResponse() {}

	public CommentResponse(CommentListInfo commentListInfo) {
		this.commentListInfo = commentListInfo;
	}

	@Override
	public String toString() {
		return "CommentResponse [commentListInfo=" + commentListInfo + "]";
	}

	public CommentListInfo getCommentListInfo() {
		return commentListInfo;
	}

	public void setCommentListInfo(CommentListInfo commentListInfo) {
		this.commentListInfo = commentListInfo;
	}

}
