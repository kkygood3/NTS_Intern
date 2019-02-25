/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.nts.reservation.common.config.ApplicationConfig;
import com.nts.reservation.login.controller.LoginController;
import com.nts.reservation.reservation.controller.ReservationApiController;
import com.nts.reservation.reservation.model.ReservationHistoryResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
@WebAppConfiguration
public class ReservationApiControllerTest {

	@Autowired
	private LoginController loginController;

	@Autowired
	private ReservationApiController reservationApiController;

	@Autowired
	private HttpSession httpSession;

	@Test
	public void getReservationHistoryResponseTest() {
		loginController.login(httpSession, "722155b@naver.com");

		ReservationHistoryResponse reservationHistoryResponse = reservationApiController
			.getReservationHistoryResponse(httpSession);

		System.out.println(reservationHistoryResponse);
	}
}
