/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.comment.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.nts.reservation.comment.model.Comment;
import com.nts.reservation.comment.model.CommentListInfo;
import com.nts.reservation.comment.model.WritedComment;
import com.nts.reservation.file.model.FileInfo;

public interface CommentService {

	static final int COUNT_LIMITED = 3;
	static final int COUNT_NOT_LIMITED = 0;

	CommentListInfo getCommentListInfo(int displayInfoId, int limitCount);

	List<Comment> getCommentList(int displayInfoId, int limitCount);

	int addComment(WritedComment writedComment, MultipartFile[] images);

	FileInfo getCommentImageSaveFileInfo(int commentImageId);
}
