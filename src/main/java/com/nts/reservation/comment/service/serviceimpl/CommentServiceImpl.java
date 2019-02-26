/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.comment.service.serviceimpl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
	
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSS");
	private static final String DIRECTORY = "c:/tmp/comment/";

	@Override
	public CommentResponse getComments(int displayInfoId, int limit) {
		List<Comment> comments = commentDao.selectComments(displayInfoId, limit);

		if (comments.size() == 0) {
			return CommentResponse.emptyCommentResponse();
		}

		for (Comment comment : comments) {
			comment.setReservationEmail(makeBlindEmail(comment.getReservationEmail()));
			comment.setReservationDate(formattingDate(comment.getReservationDate()));
			if (comment.getImageCount() == 0) {
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
		MultipartFile file = commentParam.getFile();
		if (file != null) {
			LocalDateTime now = LocalDateTime.now();
			String saveFileName = now.format(DATE_TIME_FORMATTER) + "_" + file.getOriginalFilename();
			
			int fileId = commentDao.insertFileInfo(commentParam, saveFileName);
			saveFile(commentParam.getFile(), saveFileName);
			commentDao.insertCommentImage(commentParam.getReservationInfoId(), commentId, fileId);
		}
		return commentId;
	}
	
	private void saveFile(MultipartFile file, String saveFileName) {
		try (
			FileOutputStream fileOutputStream = new FileOutputStream(DIRECTORY + saveFileName);
			
			InputStream inputStream = file.getInputStream();) {
			int readCount = 0;
			byte[] buffer = new byte[1024];
			while ((readCount = inputStream.read(buffer)) != -1) {
				fileOutputStream.write(buffer, 0, readCount);
			}
		}catch(FileNotFoundException e) {
			// TODO 무슨 exception?
			e.printStackTrace();
		}catch(IOException e1) {
			e1.printStackTrace();
		}
		
	}

	/**
	 * 이메일의 아이디 부분을 split한 후 뒷글자를 블라인드 처리
	 * @param email 
	 * @return
	 */
	private String makeBlindEmail(String email) {
		if (email.length() <= 4 && email.length() > 255) {
			// TODO Exception?
			return "비정상적인 아이디 입니다. 관리자에게 문의하세요.";
		}
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
