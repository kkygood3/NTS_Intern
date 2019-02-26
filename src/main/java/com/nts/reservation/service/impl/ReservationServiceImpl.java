/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.nts.reservation.dao.DisplayInfoDao;
import com.nts.reservation.dao.ReservationDao;
import com.nts.reservation.dto.DisplayInfoDto;
import com.nts.reservation.dto.FileDto;
import com.nts.reservation.dto.ReservationInfoDto;
import com.nts.reservation.dto.request.ReservationPriceRequestDto;
import com.nts.reservation.dto.request.ReservationRequestDto;
import com.nts.reservation.dto.request.ReservationUserCommentRequestDto;
import com.nts.reservation.service.FileIoService;
import com.nts.reservation.service.ReservationService;

/**
 * @author 육성렬
 */
@Service
@Transactional(readOnly = true)
@PropertySource("classpath:application.properties")
public class ReservationServiceImpl implements ReservationService {

	private final ReservationDao reservationDao;

	private final DisplayInfoDao displayDao;

	private final FileIoService fileIo;

	@Value("${imageDefaultPath}")
	private String imageDefaultPath;

	@Autowired
	public ReservationServiceImpl(ReservationDao reservationDao, DisplayInfoDao displayDao, FileIoService fileIo) {
		this.reservationDao = reservationDao;
		this.displayDao = displayDao;
		this.fileIo = fileIo;
	}

	/**
	 * @desc 예약 정보 입력.
	 * @param reservation
	 */
	@Transactional(readOnly = false)
	@Override
	public void addReservation(ReservationRequestDto reservation) {
		long reservationInfoId = reservationDao.insertReservation(reservation);
		List<ReservationPriceRequestDto> priceList = reservation.getPrices();
		for (ReservationPriceRequestDto price : priceList) {
			price.setReservationInfoId(reservationInfoId);
		}
		reservationDao.insertReservationPrices(priceList);
	}

	/**
	 * @desc 예약 목록 조회
	 * @param email
	 * @return List<ReservationInfoDto>
	 */
	@Override
	public List<ReservationInfoDto> getReservationList(String email) {
		List<ReservationInfoDto> list = reservationDao.selectReservationsByEmail(email);
		for (ReservationInfoDto item : list) {
			Long id = item.getDisplayInfoId();
			DisplayInfoDto display = displayDao.selectDisplayInfo(id);
			item.setDisplayInfo(display);
		}
		return list;
	}

	/**
	 * @desc 예약 갯수 조회
	 * @param email
	 * @return Integer
	 */
	@Override
	public Integer getReservationCount(String email) {
		return reservationDao.countReservationsByEmail(email);
	}

	/**
	 * @desc 예약 취소
	 * @param reservationId
	 * @return Boolean
	 */
	@Transactional(readOnly = false)
	@Override
	public void cancelReservation(Long reservationId) {
		reservationDao.updateCancelReservation(reservationId);
	}

	/**
	 * @desc 댓글 작성 등록
	 * @param requestDto
	 * @param fileList
	 * @param reservaionInfoId
	 * @throws SQLException
	 * @throws IOException
	 */
	@Transactional(readOnly = false)
	@Override
	public void addReservationUserComment(ReservationUserCommentRequestDto requestDto, Long reservationInfoId)
		throws IOException {
		Long productId = requestDto.getProductId();
		Integer score = requestDto.getScore();
		String comment = requestDto.getComment();

		List<FileDto> fileList = new ArrayList<>();
		Long reservationUserCommentId = reservationDao.insertUserComment(productId, reservationInfoId, score,
			comment);

		if (requestDto.getAttachedImages() != null) {
			try {
				for (MultipartFile image : requestDto.getAttachedImages()) {
					FileDto file = fileIo.writeMultipartFile(imageDefaultPath, image);
					fileList.add(file);
					Long fileId = reservationDao.insertFileInfo(file);
					reservationDao.insertUserCommentImage(reservationInfoId, reservationUserCommentId, fileId);
				}

			} catch (IOException exception) {
				fileIo.removeFilesForRollback(fileList);
				throw exception;
			}
		}
	}

	/**
	 * @desc 예약 정보 조회
	 * @param reservationInfoId
	 */
	@Override
	public ReservationInfoDto getReservation(Long reservationInfoId) {
		ReservationInfoDto reservation = reservationDao.selectReservation(reservationInfoId);
		Long displayId = reservation.getDisplayInfoId();
		DisplayInfoDto display = displayDao.selectDisplayInfo(displayId);
		reservation.setDisplayInfo(display);
		return reservation;
	}

	/**
	 * @desc 관람 완료된 예약 정보인지 확인.
	 * @param reservationInfoId
	 * @param email
	 */
	@Override
	public boolean findFinishReservation(Long reservationInfoId, String email) {
		return 0 < reservationDao.countFinishReservationsByEmailAndId(reservationInfoId, email);
	}

	/**
	 * @desc 코멘트 이미지 아이디에 대한 파일 정보 요청
	 * @param commentImageId
	 */
	@Override
	public FileDto getCommentImage(Long commentImageId) {
		return reservationDao.selectCommentImageById(commentImageId);
	}
}
