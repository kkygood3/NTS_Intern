/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller.api;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.myreservation.MyReservationInfo;
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
	 * @param email
	 * @return 예약 목록
	 */
	@GetMapping
	public Map<String, Object> getReservations(
		@RequestParam(name = "reservationEmail", required = true) String reservationEmail) {
		
		List<MyReservationInfo> myReservationResponse = myReservationService.getMyReservationInfoList(reservationEmail);
		
		Map<String, Object> map = new HashMap<>();
		map.put("myReservationResponse", myReservationResponse);
		
		return map;
	}

	/**
	 * 새로운 Reservation 등록
	 * @param email
	 * @param displayInfoId
	 */
	@PostMapping
	public Map<String, Object> reserve(
		@RequestParam(name = "email", required = true) String email,
		@RequestParam(name = "displayInfoId", required = true) String displayInfoId,
		HttpServletRequest request) {
		/*
		System.out.println(request.getParameter("email"));
		System.out.println(request.getParameter("displayInfoId"));
		System.out.println(request.getParameter("telephone"));
		*/
		return Collections.emptyMap();
	}

	/**
	 * Reservation을 취소
	 * @param email
	 * @param reservationInfoId
	 */
	@PutMapping("/{reservationInfoId}")
	public Map<String, Object> cancelReservation(
		@RequestParam(name = "email", required = true) String email,
		@PathVariable Integer reservationInfoId) {

		return Collections.emptyMap();
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
