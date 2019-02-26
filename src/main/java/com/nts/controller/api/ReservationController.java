/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.controller.api;

import java.rmi.ServerException;
import java.security.InvalidParameterException;
import java.text.ParseException;

import javax.naming.NoPermissionException;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.dto.reservation.ReservationParameter;
import com.mysql.jdbc.StringUtils;
import com.nts.dto.reservation.ReservationCommentParam;
import com.nts.dto.reservation.ReservationInfos;
import com.nts.exception.InvalidFormatException;
import com.nts.exception.NotFoundException;
import com.nts.service.reservation.ReservationCommentService;
import com.nts.service.reservation.ReservationService;
import com.nts.util.CheckFormat;

/**
 * @author 전연빈
 */
@RequestMapping("/api/reservations")
@RestController
public class ReservationController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final ReservationService reservationService;
	private final ReservationCommentService reservationCommentService;

	@Autowired
	public ReservationController( ReservationService reservationService,ReservationCommentService reservationCommentService) {
		this.reservationService = reservationService;
		this.reservationCommentService = reservationCommentService;
	}

	@GetMapping
	public ReservationInfos getReservationsByReservationEmail(@RequestParam(required = true) String reservationEmail)
		throws NotFoundException, InvalidFormatException {

		if (CheckFormat.validateEmailFormat(reservationEmail)) {
			return reservationService.getReservationInfoByReservationEmail(reservationEmail);
		} else {
			logger.info("reservationEmail 형식이 맞지않습니다 {}",reservationEmail);
			throw new InvalidFormatException("reservationEmail = " + reservationEmail);
		}
	}

	/**
	 * @desc reservation 등록
	 * @param reservationParameter
	 * @throws InvalidFormatException
	 * @throws ParseException
	 */
	@PostMapping
	public boolean addReservation(@RequestBody(required = true) ReservationParameter reservationParameter)
		throws InvalidFormatException, ParseException {

		if (CheckFormat.validateDateYYYYMMDDHipen(reservationParameter.getReservationYearMonthDay())
			&& CheckFormat.validateEmailFormat(reservationParameter.getReservationEmail())
			&& CheckFormat.validatePhoneNumberFormat(reservationParameter.getReservationTelephone())) {

			reservationService.addReservation(reservationParameter);

		} else {

			StringBuffer strBuffer = new StringBuffer();

			strBuffer.append("reservationEmail : ").append(reservationParameter.getReservationEmail())
				.append("reservationYearMonthDay : ").append(reservationParameter.getReservationYearMonthDay())
				.append("reservationTelephone : ").append(reservationParameter.getReservationTelephone());

			throw new InvalidFormatException(strBuffer.toString());
		}
		return true;
	}

	/**
	 * @desc 취소하기
	 * @param reservationId
	 * @param session
	 * @throws NoPermissionException
	 */
	@PutMapping("/{reservationId}")
	public boolean cancelReservation(@PathVariable(required = true) long reservationId, HttpSession session)
		throws NoPermissionException {

		if (reservationId < 0) {
			throw new InvalidParameterException("reservationId가 0보다 작습니다 ");
		}

		if (StringUtils.isEmptyOrWhitespaceOnly((String)session.getAttribute("reservationEmail"))) {
			throw new NoPermissionException("현재 로그인 상태가 아닙니다.");
		}
		reservationService.cancelReservation(reservationId, (String)session.getAttribute("reservationEmail"));
		return true;
	}

	/**
	 * @desc reservationComment 등록
	 * @param reservationInfoId
	 * @param reservationCommentParam
	 * @throws ServerException
	 */
	@PostMapping("/{reservationInfoId}/comments")
	public boolean addReservationComment(@PathVariable(required = true) int reservationInfoId,
		ReservationCommentParam reservationCommentParam)
		throws ServerException {

		reservationCommentParam.setReservationInfoId(reservationInfoId);
		reservationCommentService.addComment(reservationCommentParam);
		return true;
	}

}
