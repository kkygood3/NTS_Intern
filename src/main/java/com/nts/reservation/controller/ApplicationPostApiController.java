package com.nts.reservation.controller;
/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 */

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nts.reservation.dto.reservation.ReservationInfo;
import com.nts.reservation.dto.reservation.ReservationParam;
import com.nts.reservation.service.ReservationService;

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

@RestController
@RequestMapping(path = "/api/", method = {RequestMethod.POST})
public class ApplicationPostApiController {

	@Autowired
	private ReservationService reservationService;
	@Autowired
	private HttpSession session;

	@PostMapping(path = "/reservations", consumes = {"multipart/form-data"})
	public ReservationInfo postReservation(@RequestParam(name = "totalData", required = true) String input)
		throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		ReservationParam resInput = objectMapper.readValue(input, ReservationParam.class);
		reservationService.insertReservations(resInput);
		return new ReservationInfo();
	}

	@PostMapping(path = "/loginform", consumes = {"multipart/form-data"})
	public Boolean loginProcess(@RequestParam(name = "resrv_email", required = true) String email) {
		session.setAttribute("email", email);
		System.out.println(session.getAttribute("email"));
		return true;
	}

}
