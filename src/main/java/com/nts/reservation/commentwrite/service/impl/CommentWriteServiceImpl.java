/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.commentwrite.service.impl;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.nts.reservation.commentwrite.dao.CommentWriteDao;
import com.nts.reservation.commentwrite.dto.CommentWriteRequest;
import com.nts.reservation.commentwrite.service.CommentWriteService;

@Service
public class CommentWriteServiceImpl implements CommentWriteService {
	private static final String ROOT_DIR_COMMNET_IMAGE = "c:/tmp/img_comment/";
	private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("MMddHHmmss");

	@Autowired
	private CommentWriteDao commentWriteDaoImpl;

	@Override
	@Transactional
	public void writeReview(CommentWriteRequest commentWriteRequest) {
		int reservationUsercommentId = commentWriteDaoImpl.insertComment(commentWriteRequest);
		commentWriteRequest.setReservationUserCommentId(reservationUsercommentId);

		MultipartFile imageFile = commentWriteRequest.getImageFile();
		if(imageFile != null) {
			String fileName = DATE_FORMATTER.format(new Date()) + imageFile.getOriginalFilename();
			String fileDir = ROOT_DIR_COMMNET_IMAGE + fileName;

			try (FileOutputStream fileOut = new FileOutputStream(fileDir);
				InputStream in = imageFile.getInputStream();) {

				int readCount = 0;
				byte[] buffer = new byte[1024];

				while ((readCount = in.read(buffer)) != -1) {
					fileOut.write(buffer, 0, readCount);
				}
			} catch (Exception ex) {
				throw new RuntimeException("file Save Error");
			}

			//MultipartFile 정보를 String으로 저장
			commentWriteRequest.setFileName(fileName);
			commentWriteRequest.setSaveFileName("img_comment/" + fileName);
			commentWriteRequest.setContentType(imageFile.getContentType());

			int fileInfoId = commentWriteDaoImpl.insertFileInfo(commentWriteRequest);
			commentWriteRequest.setFileInfoId(fileInfoId);

			commentWriteDaoImpl.insertCommentImage(commentWriteRequest);

		}

	}

}
