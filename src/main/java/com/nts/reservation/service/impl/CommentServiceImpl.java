package com.nts.reservation.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.nts.reservation.dao.CommentDao;
import com.nts.reservation.dto.comment.Comment;
import com.nts.reservation.dto.comment.CommentImage;
import com.nts.reservation.dto.comment.CommentParam;
import com.nts.reservation.mappers.CommentMapper;
import com.nts.reservation.service.CommentService;
import com.nts.reservation.utils.FileIO;

/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 * 
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 *
 */
@Service
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;

	@Autowired
	private CommentMapper commentMapper;

	@Autowired
	private FileIO fileIo;

	@Override
	public CommentImage getCommentImageById(Long commentImageId) {
		return commentDao.selectCommentImageByImageId(commentImageId);
	}

	@Override
	public List<Comment> getComments(Long displayInfoId) {
		List<Comment> comments = commentMapper.selectCommentsWithImages(displayInfoId);
		System.out.println(comments.toString());
		return comments;
	}

	@Override
	public Long countExistingComments(Long reservationId) {
		return commentDao.selectCommentsCountByReservationId(reservationId);
	}

	@Override
	@Transactional(readOnly = false)
	public void postComment(CommentParam commentParam) {
		Long commentId = commentDao.insertComment(commentParam);
		File baseFolder = new File(basePath + "img_uploaded/");
		fileIo.createDirIfNotExist(baseFolder);

		File targetDir = new File(basePath + "img_uploaded/" + commentId);
		fileIo.createDirIfNotExist(targetDir);

		if (commentParam.getImageFiles() != null) {
			for (MultipartFile file : commentParam.getImageFiles()) {
				Long file_id = commentDao.insertFileInfo(file, commentId, commentParam);
				commentDao.insertCommentImageInfo(file_id, commentParam.getReservationInfoId(), commentId);
				try {
					fileIo.saveFile(basePath, commentId, file);
				} catch (Exception e) {
					if (targetDir.exists()) {
						fileIo.deleteFile(targetDir);
					}
				}
			}
		}
	}
}
