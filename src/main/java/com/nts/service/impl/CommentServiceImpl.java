package com.nts.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.dao.commentdao.CommentDao;
import com.nts.dto.commentdto.Comment;
import com.nts.dto.commentdto.CommentImage;
import com.nts.exception.ValidationException;
import com.nts.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;

	@Override
	@Transactional(readOnly = true)
	public double getAverageScoreByDisplayInfoId(int displayInfoId) throws ValidationException {
		if (displayInfoId < 0) {
			throw new ValidationException("displayInfoId : " + displayInfoId);
		}
		return commentDao.selectAverageScoreByDisplayInfoId(displayInfoId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CommentImage> getCommentImagesByDisplayInfoId(int displayInfoId) {
		if (displayInfoId < 0) {
			throw new ValidationException("displayInfoId : " + displayInfoId);
		}
		return commentDao.selectCommentImagesByDisplayInfoId(displayInfoId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Comment> getCommentsByDisplayInfoId(int displayInfoId) {
		if (displayInfoId < 0) {
			throw new ValidationException("displayInfoId : " + displayInfoId);
		}
		return commentDao.selectCommentsByDisplayInfoId(displayInfoId);

	}
}
