/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.CommentDao;
import com.nts.reservation.dto.Comment;
import com.nts.reservation.service.CommentService;

/**
* @author  : 이승수
*/
@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentDao commentDao;

	@Override
	public int setComment(Comment comment) {
		return commentDao.insertComment(comment);
	}
}
