/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.CommentDao;
import com.nts.reservation.model.Comment;
import com.nts.reservation.model.CommentMetaData;
import com.nts.reservation.model.CommentListInfo;
import com.nts.reservation.service.CommentService;

@Service
public class CommentServiceLogic implements CommentService {

	@Autowired
	private CommentDao commentDao;

	/**
	 * comment 목록과 관련 정보를 조회한 객체들을 가지는 CommentListInfo 객체를 생성후 반환
	 */
	@Override
	public CommentListInfo getCommentListInfo(int displayInfoId, boolean limit) {
		CommentMetaData commentMetaData = getCommentMetaData(displayInfoId);
		List<Comment> commentList = getCommentList(displayInfoId, limit);

		return new CommentListInfo(commentMetaData, commentList);
	}

	/** 
	 * limit parameter에 따라 특정 diplayInfoId의 comment 전체목록 혹은 갯수 제한된 목록을 dao에서 조회하여 반환
	 */
	@Override
	public List<Comment> getCommentList(int displayInfoId, boolean limit) {
		if (limit) {
			return commentDao.getLimitedCommentList(displayInfoId);
		} else {
			return commentDao.getCommentList(displayInfoId);
		}
	}

	/**
	 * 특정 displayInfoId의 comment 관련 정보를 dao에서 조회하여 반환 
	 */
	@Override
	public CommentMetaData getCommentMetaData(int displayInfoId) {
		return commentDao.getCommentMetaData(displayInfoId);
	}
}
