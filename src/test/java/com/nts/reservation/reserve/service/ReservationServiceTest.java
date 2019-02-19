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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.common.config.DbConfig;
import com.nts.reservation.reserve.dao.ReservationDao;
import com.nts.reservation.reserve.dto.ReservationParam;
import com.nts.reservation.reserve.dto.ReservationPrice;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DbConfig.class, ReservationDao.class})
public class ReservationServiceTest {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ReservationDao reservationDao;

	ReservationParam reservationParam;

	@Before
	public void setUp() {
		logger.debug("==================== before ======================");
		
		List<ReservationPrice> prices = new ArrayList<>();
		prices.add(ReservationPrice.builder().productPriceId(1).count(2).build());
		prices.add(ReservationPrice.builder().productPriceId(3).count(1).build());
		
		
		reservationParam = ReservationParam.builder()
			.displayInfoId(1)
			.productId(1)
			.reservationEmail("aewrl@naver.com")
			.reservationName("김지훈")
			.reservationTel("010-1234-4567")
			.price(prices).build();
	}

	@Test
	@Transactional
	@Ignore
	public void insertReservationParam() {
		int reservationInfoId = reservationDao.insertReservationInfo(reservationParam);
		
		reservationParam.getPrice().forEach(price->{
			price.setReservationInfoId(reservationInfoId);
			reservationDao.insertProductPrice(price);
		});
	}
	
	@After
	public void tearDown() {
		logger.debug("===================== After ========================");
	}
}
