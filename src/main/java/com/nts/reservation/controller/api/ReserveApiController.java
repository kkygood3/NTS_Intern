/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller.api;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.myreservation.MyReservationInfo;
import com.nts.reservation.dto.myreservation.MyReservationResponse;
import com.nts.reservation.dto.myreservation.ReservationType;
import com.nts.reservation.dto.reserve.ReserveRequest;
import com.nts.reservation.service.MyReservationService;
import com.nts.reservation.service.ReserveService;

@RestController
@RequestMapping("/api/reservations")
public class ReserveApiController {
	@Autowired
	ReserveService reserveResponseService;
	@Autowired
	MyReservationService myReservationService;

	/**
	 * Reservation 정보 조회
	 * @param myReservationRequest
	 * @param session
	 * @throws ParseException
	 */
	@GetMapping
	public Map<String, Object> getReservations(
		@RequestParam(name = "reservationType", required = true) ReservationType reservationType,
		@RequestParam(name = "start", required = false, defaultValue = "0") Integer start,
		@RequestParam(name = "pagingLimit", required = false, defaultValue = "100") Integer pagingLimit,
		HttpSession session){
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("myReservationResponse", myReservationService.getMyReservationResponse((String)session.getAttribute("email"), reservationType, start, pagingLimit));

		return map;
	}

	/**
	 * 새로운 Reservation 등록
	 * @param reserveRequest
	 * @param response
	 * @throws IOException
	 */
	@PostMapping
	public Map<String, Object> reserve(@RequestBody ReserveRequest reserveRequest) {
		Map<String, Object> map = new HashMap<>();
		
		if (reserveRequest.isValid() && reserveResponseService.postReserve(reserveRequest)) {
			map.put("result", "OK");
		} else {
			map.put("result", "FAIL");
		}

		return map;
	}

	/**
	 * Reservation을 취소
	 * @param reservationInfoId
	 */
	@PutMapping("/{reservationInfoId}")
	public Map<String, Object> cancelReservation(@PathVariable Integer reservationInfoId, HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		
		if (myReservationService.cancelMyReservation(reservationInfoId, (String)session.getAttribute("email"))) {
			map.put("result", "OK");
		} else {
			map.put("result", "FAIL");
		}

		return map;
	}

	/**
	 * queryForObject 요청의 결과값이 없을때 발생하는 예외 처리
	 * @return emptyMap
	 */
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public Map<String, Object> handleEmptyResult() {
		return Collections.emptyMap();
	}
}
