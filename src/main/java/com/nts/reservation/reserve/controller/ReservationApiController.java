/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.reserve.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.reserve.dto.ReservationParam;
import com.nts.reservation.reserve.dto.ReservationResponse;

@RestController
@RequestMapping(path = "/api")
public class ReservationApiController {

	@PostMapping(path = "/reservations")
	public ReservationResponse reserve(@RequestBody ReservationParam reservationParam) {
		// TODO ReservationParam -> displayInfoId, reservationEmail, reservationName, reservationTel, ReservationPrice
			// ReservationPrice -> count, productPriceId
		
		// validate param (use StringUtils) 효율적인 방법 찾아보기
		
		// product id가 안오는거같음... js에 적긴했는데 test해보기
		// js에서 Random date 보내주기 (현재날짜 +1 ~ +5)
		
		// ReservationService (@Transactional(readOnly=false) 사용) -> readOnly 기능 다시한번 보기
		// 1. insert reservation_info -> id값 꺼내옴 (table에 날짜 column의 default value -> now()로 변경하기) 
		// 2. 꺼내온 id -> insert reservation_info_price 등록 
		// 3. myRservation으로 이동 (여기 객체가 넘어가나 테스트) -> 안넘어간다면...?
		
		// Test json (for Restlet Client)
		// {"displayInfoId":"1","price":[{"productPriceId":"1","count":"2"},{"productPriceId":"3","count":"1"}],"reservationEmail":"bjakwer@naver.com","reservationName":"김지훈","reservationTel":"01012345678"}
		return ReservationResponse.builder().build();
	}
}
