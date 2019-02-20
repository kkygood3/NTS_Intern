/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.controller.api;

import java.security.InvalidParameterException;
import java.text.ParseException;

import javax.naming.NoPermissionException;
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

import com.nts.dto.reservation.ReservationParameter;
import com.mysql.jdbc.StringUtils;
import com.nts.dto.reservation.ReservationInfos;
import com.nts.exception.DisplayInfoNullException;
import com.nts.exception.InvalidFormatException;
import com.nts.service.reservation.ReservationService;
import com.nts.util.CheckFormat;

/**
 * @author 전연빈
 */
@RequestMapping("/api/reservations")
@RestController
public class ReservationController {

	private ReservationService reservationService;

	public ReservationController(@Autowired ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@GetMapping
	public ReservationInfos getReservationsByReservationEmail(@RequestParam(required = true) String reservationEmail)
		throws DisplayInfoNullException, InvalidFormatException {

		if (CheckFormat.validateEmailFormat(reservationEmail)) {
			return reservationService.getReservationInfoByReservationEmail(reservationEmail);
		} else {
			System.err.println("reservationEmail 형식이 맞지않습니다" + reservationEmail);
			throw new InvalidFormatException("reservationEmail = " + reservationEmail);
		}
	}

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

			System.err.println(strBuffer.toString());

			throw new InvalidFormatException(strBuffer.toString());
		}
		return true;
	}

	@PutMapping("/{reservationId}")
	public boolean cancelReservation(@PathVariable(required = true) long reservationId, HttpSession session) throws NoPermissionException {
		
		if (reservationId < 0) {
			throw new InvalidParameterException("reservationId가 0보다 작습니다 ");
		}
		
		if(StringUtils.isEmptyOrWhitespaceOnly((String)session.getAttribute("reservationEmail"))) {
			throw new NoPermissionException("현재 로그인 상태가 아닙니다.");
		}
		reservationService.cancelReservation(reservationId, (String)session.getAttribute("reservationEmail"));
		return true;
	}
}
