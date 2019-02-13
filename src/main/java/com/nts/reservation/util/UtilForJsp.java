/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

/**
 * @desc jsp에서 사용할 메서드들을 제공하는 클래스.
 * @author 육성렬
 */
@Component
public class UtilForJsp {
	private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy.M.d");

	/**
	 * @desc 오늘의 날짜를 리턴해줍니다. 예) 2019.2.7
	 * @return
	 */
	public static String getCurrentDate() {
		LocalDateTime currentTime = LocalDateTime.now();
		return currentTime.format(dateFormatter);
	}
}
