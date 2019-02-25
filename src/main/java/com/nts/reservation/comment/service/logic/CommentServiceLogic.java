/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.comment.service.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.nts.reservation.comment.dao.CommentDao;
import com.nts.reservation.comment.model.Comment;
import com.nts.reservation.comment.model.CommentListInfo;
import com.nts.reservation.comment.model.WritedComment;
import com.nts.reservation.comment.service.CommentService;
import com.nts.reservation.file.model.FileInfo;
import com.nts.reservation.file.service.FileService;

@Service
public class CommentServiceLogic implements CommentService {

	@Autowired
	private CommentDao commentDao;

	@Autowired
	private FileService fileService;

	/**
	 * comment 목록과 관련 정보를 조회한 객체들을 가지는 CommentListInfo 객체를 생성후 반환
	 */
	@Override
	public CommentListInfo getCommentListInfo(int displayInfoId, int limitCount) {
		CommentListInfo commentListInfo = commentDao.selectCommentListInfo(displayInfoId);
		List<Comment> commentList = getCommentList(displayInfoId, limitCount);

		commentListInfo.setCommentList(commentList);

		return commentListInfo;
	}

	/** 
	 * limit parameter에 따라 특정 diplayInfoId의 comment 전체목록 혹은 갯수 제한된 목록을 dao에서 조회하여 반환
	 */
	@Override
	public List<Comment> getCommentList(int displayInfoId, int limitCount) {
		if (isLimit(limitCount)) {
			return commentDao.selectLimitedCommentList(displayInfoId, limitCount);
		} else {
			return commentDao.selectCommentList(displayInfoId);
		}
	}

	private boolean isLimit(int limitCount) {
		return limitCount > 0;
	}

	/**
	 * comment, image file 저장
	 */
	@Override
	@Transactional
	public int addComment(WritedComment writedComment, MultipartFile[] images) {
		String commentImageDirectory = "img/";

		int reservationUserCommentId = commentDao.insertComment(writedComment);

		if (images == null) {
			return reservationUserCommentId;
		}

		for (MultipartFile image : images) {
			int fileId = fileService.storeMultipartFile(image, commentImageDirectory);
			commentDao.insertReservationUserCommentImageInfo(writedComment.getReservationId(), reservationUserCommentId,
				fileId);
		}

		return reservationUserCommentId;
	}

	/**
	 * 저장된 commentImage fileName 조회, 반환 
	 */
	@Override
	public FileInfo getCommentImageSaveFileInfo(int commentImageId) {
		return commentDao.selectCommentImageSaveFileInfo(commentImageId);
	}
}
