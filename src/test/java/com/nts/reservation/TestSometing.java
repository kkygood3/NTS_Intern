/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TestSometing {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd. (E)");
	
	@Before
	public void setUp() {

	}
	
	@Test
	public void randomDate() {
		LocalDate now = LocalDate.now();
		
		for(int i = 0 ; i < 10; i++) {
			int radomValue = new Random().nextInt(5) + 1;
			logger.debug("random date :\t{}", now.plusDays(radomValue).format(DATE_TIME_FORMATTER));
			logger.debug("max date :\t{}", now.plusDays(5).format(DATE_TIME_FORMATTER));
			logger.debug("=========================");
		}
	}
	
	
}
