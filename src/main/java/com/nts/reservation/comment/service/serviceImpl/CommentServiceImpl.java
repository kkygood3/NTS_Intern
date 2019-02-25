/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.comment.service.serviceImpl;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.nts.reservation.comment.dao.CommentDao;
import com.nts.reservation.comment.dto.Comment;
import com.nts.reservation.comment.dto.CommentImage;
import com.nts.reservation.comment.dto.CommentParam;
import com.nts.reservation.comment.dto.CommentResponse;
import com.nts.reservation.comment.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;

	@Override
	public CommentResponse getComments(int displayInfoId, int limit) {
		List<Comment> comments = commentDao.selectComments(displayInfoId, limit);

		if (comments.size() == 0) {
			return CommentResponse.emptyCommentResponse();
		}

		for (Comment comment : comments) {
			comment.setReservationEmail(makeBlindEmail(comment.getReservationEmail()));
			comment.setReservationDate(formattingDate(comment.getReservationDate()));
			if (comment.getImageCount() < 0) {
				continue;
			}
			List<CommentImage> commentImages = commentDao.selectCommentImage(comment.getCommentId());
			comment.setCommentImages(commentImages);
		}

		double avgScore = commentDao.selectCommentAvgScore(displayInfoId);
		return CommentResponse.builder()
			.comments(comments)
			.avgScore(avgScore).build();
	}

	@Override
	@Transactional
	public int insertComment(CommentParam commentParam) {
		int commentId = commentDao.insertComment(commentParam);
		int fileId = commentDao.insertFileInfo(commentParam);
		saveFile(commentParam.getFile());
		
		return commentDao.insertCommentImage(commentParam.getReservationInfoId(), commentId, fileId);
	}
	
	// TODO 임시 처리
	private void saveFile(MultipartFile file) {
		try (
			FileOutputStream fileOutputStream = new FileOutputStream("c:/tmp/" + file.getOriginalFilename());
			InputStream inputStream = file.getInputStream();) {
			int readCount = 0;
			byte[] buffer = new byte[1024];
			while ((readCount = inputStream.read(buffer)) != -1) {
				fileOutputStream.write(buffer, 0, readCount);
			}
		} catch (Exception ex) {
			throw new RuntimeException("file Save Error");
		}
	}
	
	/**
	 * 이메일의 아이디 부분을 split한 후 뒷글자를 블라인드 처리
	 * @param email 
	 * @return
	 */
	private String makeBlindEmail(String email) {
		String splicedEmail = email.split("@")[0];
		int blindPoint = splicedEmail.length() / 2;
		String blindEmail = splicedEmail.substring(0, blindPoint)
			+ splicedEmail.substring(blindPoint).replaceAll("[a-zA-Z0-9]", "*");
		return blindEmail;
	}

	private String formattingDate(String date) {
		return date.split(" ")[0].replaceAll("-", ". ") + ". ";
	}
}
