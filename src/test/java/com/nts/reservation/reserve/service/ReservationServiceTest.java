package com.nts.reservation.reserve.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.common.config.DbConfig;
import com.nts.reservation.display.dao.daoImpl.DisplayDaoImpl;
import com.nts.reservation.reserve.dao.ReservationDao;
import com.nts.reservation.reserve.dao.impl.ReservationDaoImpl;
import com.nts.reservation.reserve.dto.ReservationInfo;
import com.nts.reservation.reserve.dto.ReservationParam;
import com.nts.reservation.reserve.dto.ReservationPrice;
import com.nts.reservation.reserve.dto.ReservationResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DbConfig.class, ReservationDaoImpl.class, DisplayDaoImpl.class})
public class ReservationServiceTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceTest.class);

	@Autowired
	private ReservationDao reservationDao;

	private ReservationParam reservationParam;

	@Before
	public void setUp() {
		LOGGER.debug("==================== before ======================");

		List<ReservationPrice> prices = new ArrayList<>();
		prices.add(ReservationPrice.builder().productPriceId(1).count(2).build());
		prices.add(ReservationPrice.builder().productPriceId(505).count(1).build());

		reservationParam = ReservationParam.builder()
			.displayInfoId(1)
			.productId(1)
			.reservationEmail("")
			.reservationName("김지훈")
			.reservationTel("010-1234-4567")
			.reservationDate("2019.02.21.")
			.price(prices).build();
	}

	@Test
	@Transactional
	@Rollback
	public void insertReservationParam() {
		int reservationInfoId = reservationDao.insertReservationInfo(reservationParam);
		reservationParam.getPrice().forEach(price -> {
			price.setReservationInfoId(reservationInfoId);
			reservationDao.insertReservationPrice(price);
		});

	}
	
	@Ignore
	@Test
	public void selectReservationResponse() {
		int reservationInfoId = 1;
		ReservationInfo reservationInfo = reservationDao.selectReservationInfo(reservationInfoId);
		ReservationResponse reservationResponse = ReservationResponse.builder()
			.reservationInfo(reservationInfo)
			.price(reservationDao.selectReservationPrices(reservationInfoId))
			.build();
		LOGGER.debug("reservationResponse: {}", reservationResponse);
	}

	@After
	public void tearDown() {
		LOGGER.debug("===================== After ========================");
	}
}
