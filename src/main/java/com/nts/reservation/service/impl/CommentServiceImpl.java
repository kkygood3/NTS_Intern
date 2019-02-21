package com.nts.reservation.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.nts.reservation.dao.CommentDao;
import com.nts.reservation.dto.comment.CommentParam;
import com.nts.reservation.dto.common.CommentImage;
import com.nts.reservation.dto.detail.Comment;
import com.nts.reservation.service.CommentService;

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

	@Override
	public CommentImage getCommentImageById(Long commentImageId) {
		return commentDao.selectCommentImageByImageId(commentImageId);
	}

	@Override
	public List<Comment> getComments(Long displayInfoId) {
		List<Comment> comments = commentDao.selectComments(displayInfoId);
		for (Comment comment : comments) {
			List<CommentImage> list = commentDao.selectCommentsImagesByCommentId(comment.getCommentId());
			comment.setCommentImages(list);
		}
		return comments;
	}

	@Override
	public Long checkExistingComment(Long reservationId) {
		return commentDao.CountCommentByReservationId(reservationId);
	}

	@Override
	@Transactional(readOnly = false)
	public void postComments(CommentParam commentParam) {
		Long commentId = commentDao.insertComment(commentParam);
		File Folder = new File(basePath + "img_uploaded/" + commentId);
		if (!Folder.exists()) {
			try {
				Folder.mkdir(); //폴더 생성합니다.
				System.out.println("폴더가 생성되었습니다.");
			} catch (Exception e) {
				throw new RuntimeException("folder create error");
			}
		} else {
			System.out.println("이미 폴더가 생성되어 있습니다.");
		}
		if (commentParam.getImageFiles() != null) {
			for (MultipartFile file : commentParam.getImageFiles()) {
				Long file_id = commentDao.insertFileInfo(file, commentId, commentParam);
				commentDao.insertCommentImageInfo(file_id, commentParam.getReservationInfoId(), commentId);
				try (

					FileOutputStream fos = new FileOutputStream(
						basePath + "img_uploaded/" + commentId + "/"
							+ file.getOriginalFilename());
					InputStream is = file.getInputStream();) {
					int readCount = 0;
					byte[] buffer = new byte[1024];
					while ((readCount = is.read(buffer)) != -1) {
						fos.write(buffer, 0, readCount);
					}
					System.out.println("success saving" + file.getOriginalFilename());
				} catch (Exception ex) {
					if (Folder.exists()) {
						try {
							Folder.delete();
							System.out.println("폴더가 삭제되었습니다.");
						} catch (Exception e) {
							throw new RuntimeException("folder delete Error");
						}
					}
					throw new RuntimeException("file Save Error");
				}
			}
		}
	}
}
