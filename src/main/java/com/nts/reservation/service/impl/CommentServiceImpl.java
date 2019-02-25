package com.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.CommentMapper;
import com.nts.reservation.dto.CommentDisplayInfo;
import com.nts.reservation.dto.CommentPageInfo;
import com.nts.reservation.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentMapper commentDao;

	@Override
	public List<CommentDisplayInfo> getCommentsByProductIdWithPaging(long productId, int start, int limit) {
		return commentDao.selectFromTheProductWithPageing(productId, start, limit);
	}

	@Override
	public CommentPageInfo getCommentPageInfoByDisplayInfoId(long displayInfoId) {
		return commentDao.selectByDisplayInfoId(displayInfoId);
	}
}
