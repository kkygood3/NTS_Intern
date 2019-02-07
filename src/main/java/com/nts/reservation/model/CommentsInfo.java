/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.model;

import java.util.List;

public class CommentsInfo {

	private CommentMetaData commentMetaData;
	private List<Comment> commentList;

	public CommentsInfo() {}

	public CommentsInfo(CommentMetaData commentMetaData, List<Comment> commentList) {
		this.commentMetaData = commentMetaData;
		this.commentList = commentList;
	}

	@Override
	public String toString() {
		return "CommentsInfo [commentMetaData=" + commentMetaData + ", commentList=" + commentList + "]";
	}

	public CommentMetaData getCommentMetaData() {
		return commentMetaData;
	}

	public void setCommentMetaData(CommentMetaData commentMetaData) {
		this.commentMetaData = commentMetaData;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

}
