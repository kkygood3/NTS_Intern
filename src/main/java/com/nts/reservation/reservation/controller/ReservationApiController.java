/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.reservation.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.reservation.dto.ReservationInfoResponse;
import com.nts.reservation.reservation.dto.ReservationParam;
import com.nts.reservation.reservation.dto.ReservationUpdateParam;
import com.nts.reservation.reservation.service.ReservationService;

@RestController
@RequestMapping("/api")
public class ReservationApiController {
	private final static Logger LOG = Logger.getGlobal();

	@Autowired
	ReservationService reservationServiceImpl;

	/**
	 * 본인의 Email로 예약정보를 요청하면 관련 정보를 리턴
	 * @param reservationEmail
	 * @param session
	 * @return 올바른 정보 요청 시 해당 정보, 잘못된 정보 요청시 빈 객체 반환
	 */
	@RequestMapping(value = "/reservations", method = RequestMethod.GET)
	public ReservationInfoResponse displayInfos(
		@RequestParam(name = "reservationEmail", required = true) String reservationEmail,
		HttpSession session) {

		// 본인 여부 및 이메일 검증
		if (isInValidEmail(reservationEmail, session)) {
			LOG.warning("올바른 Email이 아닙니다. Email : " + reservationEmail );
			return new ReservationInfoResponse();
		}

		return reservationServiceImpl.getReservationInfoResponse(reservationEmail);
	}

	/**
	 * ReservationParam을 입력받아 관련 정보를 검증한 후 서버에 저장
	 * @param reserveRequest
	 * @param session
	 * @return 올바른 요청 및 DB 적용 성공 시 result : OK , 실패 시 result : FAIL
	 */
	@RequestMapping(value = "/reservations", method = RequestMethod.POST)
	public Map<String, Object> reserve(
		@RequestBody ReservationParam reserveRequest,
		HttpSession session) {
		Map<String, Object> map = new HashMap<>();

		// Param 검증, update 검증, 본인 여부 및 이메일 검증
		if (reserveRequest.isValid(reserveRequest) && reservationServiceImpl.postReserve(reserveRequest)
			&& isValidEmail(reserveRequest.getReservationEmail(), session)) {
			map.put("result", "OK");
		} else {
			LOG.warning("올바른 RequestBody가 아닙니다. RequestBody : " + reserveRequest);
			map.put("result", "FAIL");
		}

		return map;
	}

	/**
	 * ReservationUpdateParam을 입력받아 관련 정보를 검증한 후 해당 정보를 업데이트
	 * @param updateParam
	 * @param session
	 * @return 올바른 요청 및 DB 적용 성공 시 result : OK , 실패 시 result : FAIL
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Map<String, Object> reserve(
		@RequestBody ReservationUpdateParam updateParam,
		HttpSession session) {
		Map<String, Object> map = new HashMap<>();

		// Param 검증, update 검증, 본인 여부 및 이메일 검증
		if (updateParam.isValid(updateParam) && reservationServiceImpl.updateReserve(updateParam.getReservationInfoId(),
			updateParam.getReservationEmail()) && isValidEmail(updateParam.getReservationEmail(), session)) {
			map.put("result", "OK");
		} else {
			LOG.warning("올바른 RequestBody가 아닙니다. RequestBody : " + updateParam);
			map.put("result", "FAIL");
		}

		return map;
	}

	private boolean isValidEmail(String reservationEmail, HttpSession session) {
		String emailReg = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";

		// 정규식을 활용하여 이메일값을 검증하고, session에 들어있는 이메일값과 비교한다. 유효하지 않으면 false 반환
		if (reservationEmail == null || !reservationEmail.matches(emailReg)) {
			return false;
		}

		if (!reservationEmail.equals(session.getAttribute("email").toString())) {
			return false;
		}
		return true;
	}

	private boolean isInValidEmail(String reservationEmail, HttpSession session) {
		return !isValidEmail(reservationEmail, session);
	}
}
