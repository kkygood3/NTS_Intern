package com.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.CommentMapper;
import com.nts.reservation.dto.CommentDisplayInfo;
import com.nts.reservation.dto.CommentPageInfo;
import com.nts.reservation.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentMapper commentDao;

	@Override
	@Transactional
	public List<CommentDisplayInfo> getCommentsByProductIdWithPaging(long productId, int start, int limit) {
		List<CommentDisplayInfo> commentDisplayInfoList = commentDao
			.selectFromTheProductWithPageing(productId, start, limit);
		return commentDisplayInfoList;
	}

	@Override
	@Transactional
	public CommentPageInfo getCommentPageInfoByDisplayInfoId(long displayInfoId) {
		return commentDao.selectByDisplayInfoId(displayInfoId);
	}
}
