package com.nts.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
	public CommentImage getCommentImageByReservationUserCommentId(int reservationUserCommentId) {
		if (reservationUserCommentId < 0) {
			throw new ValidationException("reservationUserCommentId : " + reservationUserCommentId);
		}
		return commentDao.selectCommentImageByReservationUserCommentId(reservationUserCommentId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Comment> getCommentsByDisplayInfoId(int displayInfoId) {
		if (displayInfoId < 0) {
			throw new ValidationException("displayInfoId : " + displayInfoId);
		}
		
		List<Comment> comments = commentDao.selectCommentsByDisplayInfoId(displayInfoId);
		
		for(Comment comment : comments) {
			
			try { 
				comment.setCommentImages(getCommentImageByReservationUserCommentId(comment.getId()));
				
			} catch(EmptyResultDataAccessException e) {
				comment.setCommentImages(new CommentImage());
				
			}
		}
		return comments;
	}
}
