package com.nts.reservation.product.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.nts.reservation.common.config.ApplicationConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = ApplicationConfig.class)
public class ProductControllerTest {

	@Autowired
	private WebApplicationContext context;
	private MockMvc mock;
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductControllerTest.class);

	@Before
	public void setUp() {
		LOGGER.debug("======================== Before ===========================");
		mock = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	@Test
	public void getProducts() throws Exception {
		RequestBuilder req = get("/api/products")
			.param("displayInfoId", "1");

		mock.perform(req).andDo(print());
	}

	@After
	public void end() {
		LOGGER.debug("======================== After ===========================");
	}
}
