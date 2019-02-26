/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.reservation.controller;

import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.commons.validator.ArgumentValidator;
import com.nts.reservation.reservation.dto.ReservationInfoResponse;
import com.nts.reservation.reservation.dto.ReservationInfoType;
import com.nts.reservation.reservation.dto.ReservationPriceInfo;
import com.nts.reservation.reservation.dto.ReservationResponse;
import com.nts.reservation.reservation.dto.ReserveRequest;
import com.nts.reservation.reservation.service.ReservationService;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@RestController
@RequestMapping("/api")
public class ReservationApiController {
	@Autowired
	ReservationService reservationServiceImpl;

	// 예약 조회하기
	@GetMapping("/reservations")
	public ReservationResponse getReservationResponse(
		@RequestParam(name = "displayInfoId", required = true) int displayInfoId) {

		return reservationServiceImpl.getReservationResponse(displayInfoId);
	}

	// 예약 하기
	@PostMapping("/reservations")
	public boolean reserveProduct(
		@RequestBody ReserveRequest reserveRequest) {
		checkReserveRequest(reserveRequest);

		return reservationServiceImpl.insertReservationInfo(reserveRequest);
	}

	// 나의 예약 조회하기
	@GetMapping("/lookUpReservation")
	public ReservationInfoResponse getReservationInfoResponse(
		@RequestParam(name = "reservationInfoType", required = true) ReservationInfoType reservationInfoType,
		@RequestParam(name = "start", required = true) int start,
		@RequestParam(name = "limit", required = false, defaultValue = "3") int limit,
		HttpSession session) {

		return reservationServiceImpl.getReservationInfoResponse((String)session.getAttribute("email"),
			reservationInfoType, start, limit);
	}

	// 나의 예약 취소하기
	@PutMapping("/cancelReservation/{reservationInfoId}")
	public boolean cancelReservation(@PathVariable int reservationInfoId) {
		return reservationServiceImpl.cancelReservation(reservationInfoId);
	}

	private static final String REGULAR_KOREAN_NAME = "^[가-힣]{2,10}$";
	private static final String REGULAR_TELEPHONE = "^\\d{2,3}-\\d{3,4}-\\d{4}$";
	private static final String REGULAR_EMAIL = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";

	private static final int MAX_NAME_LENGTH = 10;
	private static final int MAX_TELEPHONE_LENGTH = 13;
	private static final int MAX_EMAIL_LENGTH = 50;

	private static final int MAX_TICKET_COUNT = 10;

	private static boolean checkReserveRequest(ReserveRequest reserveRequest) {
		String reservationName = reserveRequest.getReservationName();
		String reservationTel = reserveRequest.getReservationTel();
		String reservationEmail = reserveRequest.getReservationEmail();
		if (!checkPersonInfo(reservationName, reservationTel, reservationEmail)) {
			return false;
		}

		int displayInfoId = reserveRequest.getDisplayInfoId();
		if (!ArgumentValidator.checkDisplayInfoId(displayInfoId)) {
			return false;
		}

		int ticketCount = 0;
		List<ReservationPriceInfo> priceInfoList = reserveRequest.getReservationPriceInfoList();
		for (ReservationPriceInfo priceInfo : priceInfoList) {
			ticketCount = ticketCount + priceInfo.getCount();
		}
		if (ticketCount > MAX_TICKET_COUNT) {
			return false;
		}

		return true;
	}

	private static boolean checkPersonInfo(String reservationName, String reservationTel, String reservationEmail) {
		if (!checkName(reservationName)) {
			return false;
		}
		if (!checkTelephone(reservationTel)) {
			return false;
		}
		if (!checkEmail(reservationEmail)) {
			return false;
		}
		return true;
	}

	private static boolean checkName(String reservationName) {
		if (reservationName == null) {
			return false;
		}
		if (reservationName.length() > MAX_NAME_LENGTH) {
			return false;
		}
		if (!Pattern.matches(REGULAR_KOREAN_NAME, reservationName.trim())) {
			return false;
		}
		return true;
	}

	private static boolean checkTelephone(String reservationTel) {
		if (reservationTel == null) {
			return false;
		}
		if (reservationTel.length() > MAX_TELEPHONE_LENGTH) {
			return false;
		}
		if (!Pattern.matches(REGULAR_TELEPHONE, reservationTel.trim())) {
			return false;
		}
		return true;
	}

	private static boolean checkEmail(String reservationEmail) {
		if (reservationEmail == null) {
			return false;
		}
		if (reservationEmail.length() > MAX_EMAIL_LENGTH) {
			return false;
		}
		if (!Pattern.matches(REGULAR_EMAIL, reservationEmail.trim())) {
			return false;
		}
		return true;
	}
}
