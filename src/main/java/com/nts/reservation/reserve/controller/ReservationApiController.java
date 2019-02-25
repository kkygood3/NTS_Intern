/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.reserve.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.reserve.dto.ReservationParam;
import com.nts.reservation.reserve.dto.ReservationResponse;
import com.nts.reservation.reserve.service.ReservationService;

@RestController
@RequestMapping(path = "/api")
public class ReservationApiController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationApiController.class);

	@Autowired
	private ReservationService reservationService;

	/**
	 * 입력 받은 정보를 등록한 후, 등록된 예약 정보를 반환
	 * @param reservationParam 각 양식에 맞는 예약 정보
	 * @return ReservationResponse 등록된 예약 상품에 대한 정보
	 */
	@PostMapping(path = "/reservations")
	public ResponseEntity<ReservationResponse> reserve(@RequestBody @Valid ReservationParam reservationParam,
		BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			LOGGER.debug("{} / field : {} / code : {}", bindingResult.getFieldError().getDefaultMessage(),
				bindingResult.getFieldError().getField(), bindingResult.getFieldError().getCode());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		String reservationTel = reservationParam.getReservationTel();
		reservationParam.setReservationTel(addHyphenToPhone(reservationTel));
		return new ResponseEntity<>(reservationService.saveReservation(reservationParam), HttpStatus.OK);
	}

	/**
	 * session의 email과 reservation email을 대조 후, 일치한다면 해당 reservation을 삭제 (cancelYn = true로 변경)
	 * @param reservationInfoId 취소할 상품의 id
	 * @return ReservationResponse 예약을 취소한 상품에 대한 정보
	 * @throws HttpSessionRequiredException
	 */
	@PutMapping(path = "/reservations/{reservationInfoId}")
	public ReservationResponse cancelReservation(
		@PathVariable(name = "reservationInfoId") int reservationInfoId,
		HttpSession session) throws HttpSessionRequiredException {

		String sessionEmail = (String)session.getAttribute("email");
		return reservationService.cancelReservation(reservationInfoId, sessionEmail);
	}

	/**
	 * 입력받은 전화번호를 양식에 맞게 적용
	 */
	private String addHyphenToPhone(String phone) {
		if (phone.length() == 10) {
			return phone.substring(0, 3) + "-" + phone.substring(3, 6) + "-" + phone.substring(6);
		}
		return phone.substring(0, 3) + "-" + phone.substring(3, 7) + "-" + phone.substring(7);
	}
}
