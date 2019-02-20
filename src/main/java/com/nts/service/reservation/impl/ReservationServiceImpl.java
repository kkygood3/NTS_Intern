/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.service.reservation.impl;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.dao.reservation.ReservationRepository;
import com.nts.dto.reservation.ReservationParameter;
import com.nts.dto.reservation.Reservation;
import com.nts.dto.reservation.ReservationInfos;
import com.nts.dto.reservation.ReservationPrice;
import com.nts.exception.DisplayInfoNullException;
import com.nts.exception.NoMatchReservationException;
import com.nts.service.displayInfo.DisplayInfoService;
import com.nts.service.reservation.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private DisplayInfoService displayInfoService;

	private static final DateFormat DATE_FORMAT_YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
	private static final int CANCEL = 1;
	private static final int FAIL = 0;

	/**
	 * @desc reservationEmail 별 reservationInfo 가져오기
	 * @return reservationInfos
	 */
	@Override
	public ReservationInfos getReservationInfoByReservationEmail(String reservationEmail)
		throws DisplayInfoNullException {

		List<Reservation> reservationInfoList = reservationRepository
			.selectReservationInfoByReservationEmail(reservationEmail);

		for (Reservation reservation : reservationInfoList) {
			reservation
				.setDisplayInfo(displayInfoService.getDisplayInfoByDisplayInfoId(reservation.getDisplayInfoId()));
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
	public int addReservation(ReservationParameter reservationParameter) throws ParseException {

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
	 */
	@Override
	public int cancelReservation(long reservationId, String reservationEmail) {
		int result = reservationRepository.updateReservationCancelFlag(reservationId, CANCEL, reservationEmail);
		
		if(result == FAIL) {
			throw new NoMatchReservationException("사용자 정보와 예약자 이메일 정보가 맞지않거나, 없는 예약 정보 입니다.");
		}
		return result;
	}

	/**
	 * @desc 예약날짜 랜덤으로 0~4 숫자로 더한 뒤 삽입
	 * @param reservationYearMonthDay
	 * @return reservationDate
	 * @throws ParseException
	 */
	private String makeReservationDate(String reservationYearMonthDay) throws ParseException {

		Calendar cal = Calendar.getInstance();

		cal.setTime(DATE_FORMAT_YYYY_MM_DD.parse(reservationYearMonthDay));
		cal.add(Calendar.DATE, new Random().nextInt(5));

		return DATE_FORMAT_YYYY_MM_DD.format(cal.getTime());
	}
}
