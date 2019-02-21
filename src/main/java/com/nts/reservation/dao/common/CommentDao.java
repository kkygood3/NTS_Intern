/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.common;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nts.reservation.dto.common.Comment;

/**
 * detail, review 페이지 ajax 요청에 대응
 */
public interface CommentDao {
	/**
	 * @param displayInfoId - 조회할 displayInfo table의 ID
	 * @param pagingLimit - 한 페이지에 출력할 item 개수
	 */
	public List<Comment> selectCommentPageByDisplayInfoId(
		@Param("displayInfoId") int displayInfoId,
		@Param("start") int start,
		@Param("pagingLimit") int pagingLimit);
}
