/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.service.reservation.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.ServerException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.nts.dao.reservation.ReservationCommentRepository;
import com.nts.dto.file.FileInfo;
import com.nts.dto.reservation.ReservationCommentParam;
import com.nts.service.file.FileService;
import com.nts.service.reservation.ReservationCommentService;
import com.nts.util.FileSizeUtil;
import com.nts.util.UuidUtil;

/**
 * @author 전연빈
 */
@Service
public class ReservationCommentServiceImpl implements ReservationCommentService {

	private final ReservationCommentRepository reservationCommentRepository;

	public ReservationCommentServiceImpl(ReservationCommentRepository reservationCommentRepository) {
		this.reservationCommentRepository = reservationCommentRepository;
	}

	@Transactional(readOnly = false, rollbackFor = {ServerException.class, RuntimeException.class})
	@Override
	public int addComment(ReservationCommentParam reservationCommentParam)
		throws ServerException {
		
		int reservationUserCommentId = reservationCommentRepository.insertIntoReservationUserComment(reservationCommentParam);

		if (reservationCommentParam.getAttachedImage() != null) {
			
			String fileName = getImageFileName(reservationCommentParam.getAttachedImage().getContentType());
			addFile(reservationCommentParam.getAttachedImage(), fileName);
			
			FileInfo fileInfo = new FileInfo();
			
			fileInfo.setContentType(reservationCommentParam.getAttachedImage().getContentType());
			fileInfo.setSaveFileName(IMAGE_FILE_FOLDER_PATH +fileName);
			fileInfo.setFileName(fileName);
			
			int fileId = reservationCommentRepository.insertIntoFileInfo(fileInfo);
			
			reservationCommentRepository.insertIntoReservationUserCommentImage(reservationCommentParam.getReservationInfoId(), reservationUserCommentId, fileId);
		}

		return 0;
	}

	private void addFile(MultipartFile imageFile, String fileName) throws ServerException {
		try (
			FileOutputStream outputStream = new FileOutputStream(FileService.FILE_PATH + IMAGE_FILE_FOLDER_PATH + fileName);
			InputStream inputStream = imageFile.getInputStream()) {

			int readCount = 0;
			byte[] buffer = new byte[FileSizeUtil.KILLO_BYTE];

			while ((readCount = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, readCount);
			}
		} catch (FileNotFoundException e) {
			throw new ServerException("파일 요청을 하지않음" , e);
		} catch (IOException e) {
			throw new ServerException("서버 파일 요청 에러" , e);
		}
	}
	
	private String getImageFileName(String contentType) throws ServerException {
		return UuidUtil.getUuid() + contentType.replaceAll("image/", ".");
	}
}
