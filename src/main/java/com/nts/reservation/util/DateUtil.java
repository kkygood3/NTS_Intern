/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 날짜 관련 유틸 클래스
 * @author jinwoo.bae
 */
public class DateUtil {
	/**
	 * 오늘이 지난 날짜인지 판별
	 */
	public static Boolean isAfterToday(String dateString) {
		LocalDateTime  today = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
		LocalDateTime  date = LocalDateTime.parse(dateString, dtf);
		return today.isAfter(date);
	}
	
	/**
	 * 오늘날짜 + 임의의 날짜 더한값 가져오기  
	 */
	public static String getTodayWithPlusRandomDays(int min, int max) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
		return dtf.format(LocalDate.now().plusDays(randomNum));
	}
}
