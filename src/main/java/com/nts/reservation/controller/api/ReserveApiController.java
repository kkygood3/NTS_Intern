/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller.api;

import java.util.Collections;
import java.util.Map;

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

import com.nts.reservation.service.ReserveResponseService;

@RestController
@RequestMapping("/api/reservations")
public class ReserveApiController {
	@Autowired
	ReserveResponseService reserveResponseService;
	
	/**
	 * Reservation 정보 조회
	 * @param email
	 * @return 예약 목록
	 */
	@GetMapping
	public Map<String, Object> getReservations(
		@RequestParam(name = "email", required = true) String email) {

		return Collections.emptyMap();
	}

	/**
	 * 새로운 Reservation 등록
	 * @param email
	 * @param displayInfoId
	 */
	@PostMapping
	public Map<String, Object> reserve(
		@RequestParam(name = "email", required = true) String email,
		@RequestParam(name = "displayInfoId", required = true) String displayInfoId) {

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
