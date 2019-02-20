/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.nts.reservation.common.config.ApplicationConfig;
import com.nts.reservation.reservation.model.Reservation;
import com.nts.reservation.reservation.model.ReservationHistory;
import com.nts.reservation.reservation.model.ReservationPrice;
import com.nts.reservation.reservation.service.ReservationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
@WebAppConfiguration
public class ReservationServiceTest {

	@Autowired
	private ReservationService reservationService;

	@Test
	public void addReservationTest() {
		Reservation reservation = new Reservation();
		reservation.setDisplayInfoId(1);
		reservation.setProductId(1);
		reservation.setReservationDate("1996.10.18");
		reservation.setReservationEmail("722155b@naver.com");
		reservation.setReservationName("별");
		reservation.setReservationTel("123456789");

		ReservationPrice reservationPrice1 = new ReservationPrice();
		reservationPrice1.setCount(10);
		reservationPrice1.setProductPriceId(1);

		reservation.getReservationPriceList().add(reservationPrice1);

		ReservationPrice reservationPrice2 = new ReservationPrice();
		reservationPrice2.setCount(30);
		reservationPrice2.setProductPriceId(2);

		reservation.getReservationPriceList().add(reservationPrice2);

		int n = reservationService.addReservation(reservation);

		System.out.println(n);

	}

	@Test
	public void getReservationHistoryListTest() {
		List<ReservationHistory> reservationHistoryList = reservationService
			.getReservationHistoryList("722155b@naver.com");

		System.out.println(reservationHistoryList);
	}

	@Test
	public void cancelReservationTest() {
		int reservationId = 0;
		reservationService.modifyReservationToCancel(reservationId);
	}
}
