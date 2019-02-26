/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller.api;

import static com.nts.reservation.constant.ParameterDefaultValue.*;

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
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.annotation.PageDefault;
import com.nts.reservation.constant.ReservationStatusType;
import com.nts.reservation.dto.param.CommentParamDto;
import com.nts.reservation.dto.param.PageDto;
import com.nts.reservation.dto.param.ReservationParamDto;
import com.nts.reservation.dto.response.ReservationResponseDto;
import com.nts.reservation.exception.InValidationException;
import com.nts.reservation.service.ReservationService;

/**
 * 예약 API
 * @author jinwoo.bae
 */
@RestController
@RequestMapping("/api/reservations")
public class ReservationApiController {
	@Autowired
	private ReservationService reservationService;

	/**
	 * 예약하기
	 */
	@PostMapping
	public Map<String, Object> postReservation(@RequestBody ReservationParamDto reservationParam) {
		if (reservationParam.isValid()) {
			throw new InValidationException("입력값이 올바르지 않습니다.");
		}
		reservationService.makeReservation(reservationParam);
		return Collections.singletonMap("isSuccess", true);
	}

	/**
	 * 예약 취소하기
	 */
	@PutMapping("/{reservationId}")
	public Map<String, Object> cancelReservation(@PathVariable int reservationId) {
		reservationService.cancelReservation(reservationId);
		return Collections.singletonMap("isSuccess", true);
	}

	/**
	 * 예약상태(예정,완료,취소.)에 따라 예약리스트들을 가져옵니다.
	 */
	@GetMapping("/{status}")
	public ReservationResponseDto getReservationsByStatus(
		@PathVariable String status,
		@PageDefault(limit = RESERVATIONS_LIMIT) PageDto page,
		HttpSession session) {
		String reservationEmail = (String)session.getAttribute("reservationEmail");

		return reservationService.getReservationResponse(reservationEmail, ReservationStatusType.valueOf(status), page);
	}

	/**
	 * 상품평 등록
	 */
	@PostMapping("/{reservationId}/comments")
	public Map<String, Object> postComment(CommentParamDto commentParam) {
		if (!commentParam.isValid()) {
			throw new InValidationException("입력값이 올바르지 않습니다.");
		}
		reservationService.makeComment(commentParam);
		return Collections.singletonMap("isSuccess", true);
	}
}
