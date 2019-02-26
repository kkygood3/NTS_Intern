package com.nts.reservation.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateGenerator {
	private static SimpleDateFormat dateFormat;
	private static Calendar calendar;
	static {
		//공연 정보 날짜를 오늘부터 1~5일후의 날짜로 무작위 생성.
		dateFormat = new SimpleDateFormat("yyyy.MM.dd");
		calendar = Calendar.getInstance();
	}
	
	public static String getRandomDate() {
		calendar.add(Calendar.DAY_OF_MONTH, (int)((Math.random() * 5)) + 1);
		return dateFormat.format(calendar.getTime());
	}
}
