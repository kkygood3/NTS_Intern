/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.controller.api;

import static com.nts.reservation.dto.request.regex.RegexPattern.EMAIL_REGEX;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.nts.reservation.dto.ReservationInfoDto;
import com.nts.reservation.dto.request.ReservationRequestDto;
import com.nts.reservation.dto.response.MyReservationResponseDto;
import com.nts.reservation.exception.BadRequestException;
import com.nts.reservation.service.ReservationService;

/**
 * @author 육성렬
 */

@RestController
@RequestMapping(path = "/api/reservations")
public class ReservationApiController {
	@Autowired
	private ReservationService reservationService;

	private final Pattern emailPattern = Pattern.compile(EMAIL_REGEX);

	/**
	 * @desc 예약 추가.
	 * @param requestParams
	 * @param bindingResult
	 * @param uriBuilder
	 * @return
	 * @throws BindException
	 */
	@PostMapping(produces = "application/json")
	public ResponseEntity<Map<String, String>> postReservation(@Valid @RequestBody ReservationRequestDto requestParams,
		BindingResult bindingResult, UriComponentsBuilder uriBuilder)
		throws BindException {

		if (bindingResult.hasErrors()) {
			throw new BindException(bindingResult);
		}

		URI redirectUri = uriBuilder.path("/")
			.build()
			.toUri();

		reservationService.addReservation(requestParams);
		return new ResponseEntity<Map<String, String>>(Collections.singletonMap("redirectUri", redirectUri.toString()),
			HttpStatus.OK);
	}

	/**
	 * @desc 이메일에 해당하는 예약 정보 조회
	 * @param reservationEmail
	 * @return
	 * @throws BadRequestException
	 */
	@GetMapping
	public MyReservationResponseDto getReservationResponse(
		@RequestParam String reservationEmail) throws BadRequestException {

		if (!emailPattern.matcher(reservationEmail).find()) {
			throw new BadRequestException();
		}

		List<ReservationInfoDto> list = reservationService.getReservationList(reservationEmail);
		int count = reservationService.getReservationCount(reservationEmail);
		return new MyReservationResponseDto(list, count);
	}

	/**
	 * @desc 예약 취소.
	 * @param reservationId
	 * @return
	 * @throws BadRequestException
	 */
	@PutMapping(path = "/{reservationId}")
	public Long putCancelReservation(@PathVariable Long reservationId) throws BadRequestException {

		if (!reservationService.cancelReservation(reservationId)) {
			throw new BadRequestException();
		}
		return reservationId;
	}
}
