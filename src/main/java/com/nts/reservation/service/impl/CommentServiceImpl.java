/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.nts.reservation.dao.CommentDao;
import com.nts.reservation.dto.Comment;
import com.nts.reservation.dto.CommentImage;
import com.nts.reservation.dto.FileInfo;
import com.nts.reservation.service.CommentService;
import com.nts.reservation.service.FileIoService;

/**
* @author  : 이승수
*/
@Service
@Transactional
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private FileIoService fileIoService;

	@Override
	public void addComment(Comment comment, List<MultipartFile> files) throws IOException {
		int reservationInfoId = comment.getReservationInfoId();
		int reservationUserCommentId = commentDao.getIdAfterInsertComment(comment);

		if (files != null) {
			for (MultipartFile file : files) {
				FileInfo fileInfo = fileIoService.createFileInfo(file);

				int fileInfoId = fileIoService.setFileInfo(fileInfo);

				CommentImage commentImage = new CommentImage();
				commentImage.setReservationInfoId(reservationInfoId);
				commentImage.setReservationUserCommentId(reservationUserCommentId);
				commentImage.setFileId(fileInfoId);

				commentDao.insertCommentImage(commentImage);
			}
		}
	}
}
