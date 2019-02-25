/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.impl;

import java.io.FileOutputStream;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.nts.reservation.dao.reviewwrite.ReviewWrtieDao;
import com.nts.reservation.dto.reviewwrite.ReviewWriteRequest;
import com.nts.reservation.dto.reviewwrite.ReviewWriteResponse;
import com.nts.reservation.service.ReviewWriteService;

@Service
public class ReviewWriteServiceImpl implements ReviewWriteService {
	@Autowired
	ReviewWrtieDao reviewWriteDao;

	@Override
	public ReviewWriteResponse getReviewWriteResponse(int reservationInfoId) {
		return reviewWriteDao.selectReviewWrite(reservationInfoId);
	}

	@Override
	@Transactional(readOnly = false)
	public void writeReview(ReviewWriteRequest reviewWriteRequest) {
		reviewWriteDao.insertComment(reviewWriteRequest);

		MultipartFile imageFile = reviewWriteRequest.getImageFile();
		if (imageFile != null) {
			String fileName = imageFile.getOriginalFilename();

			try (
				FileOutputStream fos = new FileOutputStream("c:/tmp/img/" + imageFile.getOriginalFilename());
				InputStream is = imageFile.getInputStream();) {
				int readCount = 0;
				byte[] buffer = new byte[1024];
				while ((readCount = is.read(buffer)) != -1) {
					fos.write(buffer, 0, readCount);
				}
			} catch (Exception ex) {
				throw new RuntimeException("file Save Error");
			}

			reviewWriteRequest.setFileName(fileName);
			reviewWriteRequest.setSaveFileName("img/" + fileName);
			reviewWriteRequest.setContentType("image/" + fileName.substring(fileName.lastIndexOf(".") + 1));

			reviewWriteDao.insertFileInfo(reviewWriteRequest);
			reviewWriteDao.insertCommentImage(reviewWriteRequest);
		}

	}
}
