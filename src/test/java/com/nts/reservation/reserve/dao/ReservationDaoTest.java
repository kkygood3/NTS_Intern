package com.nts.reservation.reserve.dao;

import static org.junit.Assert.assertEquals;

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
import com.nts.reservation.reserve.dao.impl.ReservationDaoImpl;
import com.nts.reservation.reserve.dto.ReservationParam;
import com.nts.reservation.reserve.dto.ReservationPrice;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DbConfig.class, ReservationDaoImpl.class})
public class ReservationDaoTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationDaoTest.class);

	@Autowired
	ReservationDao reservationDao;

	ReservationParam reservationParam;
	ReservationPrice reservationPrice;

	@Before
	public void setUp() {
		LOGGER.debug("==================== before ======================");

		reservationPrice = ReservationPrice.builder()
			.reservationInfoId(1)
			.productPriceId(1)
			.count(2)
			.build();

		reservationParam = ReservationParam.builder()
			.productId(1)
			.displayInfoId(1)
			.reservationName("도란이")
			.reservationTel("010-1234-5678")
			.reservationEmail("ehfksl@naver.com")
			.reservationDate("2019.03.23.")
			.build();
	}

	@Test
	@Transactional
	@Rollback
	@Ignore
	public void insertResrvationInfo() {
		// XXX auto increment issue 해결방법?
		LOGGER.debug("return id : {}", reservationDao.insertReservationInfo(reservationParam));
	}

	@Test
	@Transactional
	@Rollback
	@Ignore
	public void insertProductPrice() {
		assertEquals(1, reservationDao.insertReservationPrice(reservationPrice));
	}

	@Test
	public void selectReservationInfos() {
		// TODO 출력값이 객체일 경우 성공/실패 확인?
		LOGGER.debug("selectReservationInfos : {}", reservationDao.selectReservationInfos("aewrl@naver.com"));
	}

	@Test
	public void selectReservationInfo() {
		LOGGER.debug("selectReservationInfo : {}", reservationDao.selectReservationInfo(1));
	}

	@Test
	public void selectReservationPrice() {
		LOGGER.debug("selectReservationPrice : {}", reservationDao.selectReservationPrices(1));
	}

	@After
	public void tearDown() {
		LOGGER.debug("===================== After ========================");
	}
}
