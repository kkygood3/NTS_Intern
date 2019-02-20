/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TestSomething {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestSomething.class);
	
	DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd. (E)");
	
	@Before
	public void setUp() {

	}
	
	@Test
	@Ignore
	public void randomDate() {
		LocalDate now = LocalDate.now();
		
		for(int i = 0 ; i < 10; i++) {
			int radomValue = new Random().nextInt(5) + 1;
			LOGGER.debug("random date :\t{}", now.plusDays(radomValue).format(DATE_TIME_FORMATTER));
			LOGGER.debug("max date :\t{}", now.plusDays(5).format(DATE_TIME_FORMATTER));
			LOGGER.debug("=========================");
		}
	}
	
	@Test
	public void compareDate() {
		String date = "2019.02.19.";
		LOGGER.debug("compare Date : {}", date.compareTo(LocalDate.now().format(DATE_TIME_FORMATTER).split(" ")[0]));
		assertEquals(1, date.compareTo(LocalDate.now().minusDays(1).format(DATE_TIME_FORMATTER).split(" ")[0]));
	}
	
	
}
