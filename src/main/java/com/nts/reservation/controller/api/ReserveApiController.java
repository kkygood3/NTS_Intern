/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller.api;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;
import java.util.Map;

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

import com.nts.reservation.dto.myreservation.ReservationType;
import com.nts.reservation.dto.reserve.ReserveRequest;
import com.nts.reservation.dto.reviewwrite.ReviewWriteRequest;
import com.nts.reservation.property.CommonProperties;
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
		@RequestParam(name = "start", required = true) Integer start,
		@RequestParam(name = "pagingLimit", required = false, defaultValue = CommonProperties.MY_RESERVATION_DEFAULT_PAGING_LIMIT) Integer pagingLimit,
		HttpSession session){
		
		if (start < 0) {
			start = 0;
		}
		
		return Collections.singletonMap("myReservationResponse", myReservationService.getMyReservationResponse((String)session.getAttribute("email"), reservationType, start, pagingLimit));
	}

	/**
	 * 새로운 Reservation 등록
	 * @param reserveRequest
	 * @param response
	 * @throws IOException
	 */
	@PostMapping
	public Map<String, Object> reserve(@RequestBody ReserveRequest reserveRequest) {
		
		String result = "FAIL";
		if (reserveRequest.isValid()) {
			reserveResponseService.registerReserve(reserveRequest);
			result = "OK";
		}

		return Collections.singletonMap("result", result);
	}

	/**
	 * Reservation을 취소
	 * @param reservationInfoId
	 */
	@PutMapping("/{reservationInfoId}")
	public Map<String, Object> cancelReservation(@PathVariable Integer reservationInfoId, HttpSession session) {
		
		String result = "FAIL";
		if (myReservationService.cancelMyReservation(reservationInfoId, (String)session.getAttribute("email"))) {
			result = "OK";
		}

		return Collections.singletonMap("result", result);
	}
	
	/**
	 * Comment 등록
	 * @param reservationInfoId
	 */
	@PostMapping("/{reservationInfoId}/comments")
	public Map<String, Object> registerComment(@PathVariable Integer reservationInfoId,
		ReviewWriteRequest reviewWriteRequest) {
		System.out.println("호출 성공 " + reviewWriteRequest);
		
		
		
		return Collections.emptyMap();
	}
}
