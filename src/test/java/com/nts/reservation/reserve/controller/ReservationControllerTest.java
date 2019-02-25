package com.nts.reservation.reserve.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nts.reservation.common.config.ApplicationConfig;
import com.nts.reservation.reserve.dto.ReservationParam;
import com.nts.reservation.reserve.dto.ReservationPrice;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = ApplicationConfig.class)
public class ReservationControllerTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationControllerTest.class);

	@Autowired
	private WebApplicationContext context;

	private MockMvc mock;
	private ObjectMapper objectMapper;
	private ReservationParam reservationParam;
	private MockHttpSession session;

	@Before
	public void setUp() {
		LOGGER.debug("======================== Before ===========================");
		mock = MockMvcBuilders.webAppContextSetup(this.context).build();
		objectMapper = new ObjectMapper();

		session = new MockHttpSession();
		session.setAttribute("email", "qweqwee@naver.com");

		List<ReservationPrice> prices = new ArrayList<>();
		prices.add(ReservationPrice.builder().productPriceId(1).count(2).build());
		prices.add(ReservationPrice.builder().productPriceId(2).count(1).build());

		reservationParam = ReservationParam.builder()
			.displayInfoId(0)
			.productId(0)
			.reservationName("로라정")
			.reservationEmail("qwee@naver.com")
			.reservationTel("0191112222")
			.reservationDate("2019.02.21.")
			.price(prices).build();
	}

	@Test
	@Transactional
	public void getProducts() throws Exception {
		RequestBuilder requestBuilder = post("/api/reservations")
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.content(objectMapper.writeValueAsString(reservationParam));

		mock.perform(requestBuilder).andExpect(status().isOk());
	}

	@Test
	public void cancelReservation() throws Exception {
		RequestBuilder requestBuilder = put("/api/reservations/16").session(session);
		mock.perform(requestBuilder).andExpect(status().isOk());
	}

	@Test
	public void myreservation() throws Exception {
		RequestBuilder requestBuilder = get("/myreservation");
		mock.perform(requestBuilder).andExpect(status().isOk());
	}

	@After
	public void end() {
		LOGGER.debug("======================== After ===========================");
	}
}
