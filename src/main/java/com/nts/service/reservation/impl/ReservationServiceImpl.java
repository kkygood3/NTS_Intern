/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.service.reservation.impl;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import javax.naming.NoPermissionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.dao.reservation.ReservationRepository;
import com.nts.dto.reservation.ReservationParameter;
import com.nts.dto.reservation.Reservation;
import com.nts.dto.reservation.ReservationInfos;
import com.nts.dto.reservation.ReservationPrice;
import com.nts.exception.NotFoundException;
import com.nts.service.displayInfo.DisplayInfoService;
import com.nts.service.reservation.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

	private static final int CANCEL = 1;
	private static final int FAIL = 0;

	private final ReservationRepository reservationRepository;
	private final DisplayInfoService displayInfoService;
	
	@Autowired
	public ReservationServiceImpl(ReservationRepository reservationRepository, DisplayInfoService displayInfoService) {
		this.reservationRepository = reservationRepository;
		this.displayInfoService = displayInfoService;
	}


	/**
	 * @desc reservationEmail 별 reservationInfo 가져오기
	 * @return reservationInfos
	 */
	@Override
	public ReservationInfos getReservationInfoByReservationEmail(String reservationEmail)
		throws NotFoundException {

		List<Reservation> reservationInfoList = reservationRepository
			.selectReservationInfoByReservationEmail(reservationEmail);

		for (Reservation reservation : reservationInfoList) {
			reservation.setDisplayInfo(displayInfoService.getDisplayInfoByDisplayInfoId(reservation.getDisplayInfoId()));
		}

		ReservationInfos reservationInfos = new ReservationInfos();

		reservationInfos.setReservations(reservationInfoList);
		reservationInfos.setSize(reservationInfoList.size());

		return reservationInfos;
	}

	/**
	 * @desc reservation 삽입
	 * @param reservationParameter
	 * @throws ParseException 
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = {SQLException.class})
	public int addReservation(ReservationParameter reservationParameter){

		reservationParameter.setReservationYearMonthDay(makeReservationDate(reservationParameter.getReservationYearMonthDay()));
		long reservationInfoId = reservationRepository.insertReservation(reservationParameter);

		for (ReservationPrice reservationPrice : reservationParameter.getPrices()) {
			reservationPrice.setReservationInfoId(reservationInfoId);

			reservationRepository.insertReservationPrice(reservationPrice);
		}
		return 1;
	}

	/**
	 * @desc 취소 하기 
	 * @param reservationId
	 * @throws NoPermissionException 
	 */
	@Override
	public int cancelReservation(long reservationId, String reservationEmail) throws NoPermissionException {
		int result = reservationRepository.updateReservationCancelFlag(reservationId, CANCEL, reservationEmail);
		
		if(result == FAIL) {
			throw new NoPermissionException("사용자 정보와 예약자 이메일 정보가 맞지않거나, 없는 예약 정보 입니다.");
		}
		return result;
	}

	/**
	 * @desc 예약날짜 랜덤으로 0~4 숫자로 더한 뒤 삽입
	 * @param reservationYearMonthDay
	 * @return reservationDate
	 * @throws ParseException
	 */
	private String makeReservationDate(String reservationYearMonthDay) {

		return LocalDate.parse(reservationYearMonthDay).plusDays(new Random(5).nextInt()).toString();
	}

	/**
	 * @desc 예약한 사람만 reservation 데이터 넘겨줌
	 * @param reservationEmail
	 * @param reservationInfoId
	 */
	@Override
	public Reservation getReservedInfo(String reservationEmail, int reservationInfoId) throws NoPermissionException {
		return reservationRepository.selectReserved(reservationEmail, reservationInfoId);
	}
}
