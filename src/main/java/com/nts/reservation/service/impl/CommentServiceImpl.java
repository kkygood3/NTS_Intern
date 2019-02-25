package com.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.CommentMapper;
import com.nts.reservation.dto.CommentDisplayItem;
import com.nts.reservation.dto.CommentPageInfo;
import com.nts.reservation.dto.FileInfo;
import com.nts.reservation.dto.ReservationUserComment;
import com.nts.reservation.dto.ReservationUserCommentImage;
import com.nts.reservation.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentMapper commentDao;

	@Override
	public List<CommentDisplayItem> getCommentsByProductIdWithPaging(long productId, int start, int limit) {
		return commentDao.selectFromTheProductWithPageing(productId, start, limit);
	}

	@Override
	public CommentPageInfo getCommentPageInfoByProductId(long productId) {
		return commentDao.selectCommentPageInfoByProductId(productId);
	}
	
	@Override
	@Transactional(readOnly = false)
	public ReservationUserComment addReservationUserComment(ReservationUserComment comment, FileInfo image) {
		return null;
	}
}
