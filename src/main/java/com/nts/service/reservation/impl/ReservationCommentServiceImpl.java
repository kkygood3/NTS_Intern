/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.service.reservation.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.nts.dao.reservation.ReservationCommentRepository;
import com.nts.dto.file.FileInfo;
import com.nts.dto.reservation.ReservationCommentParam;
import com.nts.service.reservation.ReservationCommentService;
import com.nts.util.UuidUtil;

/**
 * @author 전연빈
 */
@Service
public class ReservationCommentServiceImpl implements ReservationCommentService {

	private static final String FILE_PATH = "C:\\Users\\USER\\eclipse-workspace\\2019_1st_intern6_test\\2019_1st_intern6_test\\src\\main\\webapp\\img\\";

	private final ReservationCommentRepository reservationCommentRepository;

	public ReservationCommentServiceImpl(ReservationCommentRepository reservationCommentRepository) {
		this.reservationCommentRepository = reservationCommentRepository;
	}

	@Transactional(readOnly = false, rollbackFor = {NoSuchAlgorithmException.class, FileNotFoundException.class, IOException.class, SQLException.class})
	@Override
	public int addComment(ReservationCommentParam reservationCommentParam)
		throws NoSuchAlgorithmException, FileNotFoundException, IOException {
		
		int reservationUserCommentId = reservationCommentRepository.insertIntoReservationUserComment(reservationCommentParam);

		if (reservationCommentParam.getAttachedImage() != null) {
			String fileName = addFile(reservationCommentParam.getAttachedImage());
			
			FileInfo fileInfo = new FileInfo();
			
			fileInfo.setContentType(reservationCommentParam.getAttachedImage().getContentType());
			fileInfo.setSaveFileName("img/" +fileName);
			fileInfo.setFileName(fileName);
			
			int fileId = reservationCommentRepository.insertIntoFileInfo(fileInfo);
			
			reservationCommentRepository.insertIntoReservationUserCommentImage(reservationCommentParam.getReservationInfoId(), reservationUserCommentId, fileId);
		}

		return 0;
	}

	private String addFile(MultipartFile imageFile) throws NoSuchAlgorithmException, FileNotFoundException, IOException {
		String fileName = UuidUtil.getUuid() + imageFile.getContentType().replaceAll("image/", ".");
		try (
			FileOutputStream outputStream = new FileOutputStream(FILE_PATH + fileName);
			InputStream inputStream = imageFile.getInputStream()) {

			int readCount = 0;
			byte[] buffer = new byte[1024];

			while ((readCount = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, readCount);
			}
		}
		return fileName;
	}
}
