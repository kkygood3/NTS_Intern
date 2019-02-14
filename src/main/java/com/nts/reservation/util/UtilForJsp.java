/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.nts.reservation.dto.tag.PriceTag;

/**
 * @desc jsp에서 사용할 메서드들을 제공하는 클래스.
 * @author 육성렬
 */
@Component
public class UtilForJsp {
	private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy.M.d");
	private static Random random = new Random();

	/**
	 * @desc 오늘의 날짜에 1~5일을 추가해준 값을 리턴해줍니다. 예) 2019.2.7
	 * @return
	 */
	public static String getReservationDate() {
		int addDay = random.nextInt(4) + 1;
		LocalDateTime reservationTime = LocalDateTime.now().plusDays(addDay);
		return reservationTime.format(dateFormatter);
	}

	public static String getTicketNameByType(String type) {
		return PriceTag.valueOf(type).getName();
	}
}
