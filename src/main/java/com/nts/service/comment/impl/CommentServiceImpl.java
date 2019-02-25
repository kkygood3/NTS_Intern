/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.service.comment.impl;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nts.dao.comment.CommentRepository;
import com.nts.dto.comment.Comment;
import com.nts.dto.comment.Comments;
import com.nts.dto.file.FileInfo;
import com.nts.service.comment.CommentService;

/**
 * @author 전연빈
 */
@Service
public class CommentServiceImpl implements CommentService {

	private static final String FILE_PATH = "/Users/USER/git/2019_1st_intern6/src/main/webapp/";
	
	private final CommentRepository commentRepository;
	
	public CommentServiceImpl(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}

	/**
	 * @desc product Id 별 comment list 불러오기
	 * @param productId
	 * @return comment list
	 */
	@Override
	public List<Comment> getCommentListByProductId(int productId) {

		List<Comment> commentList = commentRepository.selectCommentByProductId(productId);

		for (Comment comment : commentList) {
			if (comment.hasImageFile()) {
				comment.setCommentImage(commentRepository.selectCommentImagesByCommentId(comment.getCommentId()));
			}
		}
		return commentList;
	}

	/**
	 * @desc productId 별 comment 평균 점수 가져오기
	 * @param productId
	 * @return averageScore
	 */
	@Override
	public double getAverageScoreByProductId(int productId) {
		return commentRepository.selectCommentAverageByProductId(productId);
	}

	/**
	 * @desc comments, averageScore 가져오기
	 * @param productId
	 * @return comments
	 */
	@Override
	public Comments getComments(int productId) {

		Comments comments = new Comments();

		comments.setAverageScore(getAverageScoreByProductId(productId));
		comments.setComments(getCommentListByProductId(productId));

		return comments;
	}

	@Override
	public FileInfo getFileInfoByCommentId(int commentId) throws FileNotFoundException {
		
		FileInfo fileInfo = commentRepository.selectFileInfoByCommentId(commentId);
		
		fileInfo.setSaveFileName(FILE_PATH + fileInfo.getSaveFileName());
		return fileInfo;
	}

}
