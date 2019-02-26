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
import com.nts.reservation.property.Properties;
import com.nts.reservation.service.ReviewWriteService;
import com.nts.reservation.utils.FileUtils;

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
			String fileName = FileUtils.addRandomSuffix(imageFile.getOriginalFilename());
			String fileDir = Properties.ROOT_DIR_COMMNET_IMAGE + fileName;

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

			//무작위 문자열을 포함한 파일명을 저장
			reviewWriteRequest.setFileName(fileName);
			reviewWriteRequest.setSaveFileName("img_map/" + fileName);
			reviewWriteRequest.setContentType(imageFile.getContentType());

			reviewWriteDao.insertFileInfo(reviewWriteRequest);
			reviewWriteDao.insertCommentImage(reviewWriteRequest);
		}
	}
}
