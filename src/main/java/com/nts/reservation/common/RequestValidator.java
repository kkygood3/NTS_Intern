/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.common;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.nts.reservation.dto.reserve.ReservePrice;
import com.nts.reservation.dto.reserve.ReserveRequest;
import com.nts.reservation.dto.reviewwrite.ReviewWriteRequest;
import com.nts.reservation.property.CommonProperties;

/**
 * Reservation 프로젝트에서 사용하는 특정 형식 String이 유효한지 검증하는 Class
 * static method를 사용함
 */
public class RequestValidator {
	/**
	 * 예약 등록 정보 검증
	 * @return 티켓 수와 사용자 정보가 올바르다면 TRUE
	 */
	public static boolean validateReserveRequest(ReserveRequest reserveRequest) {
		List<ReservePrice> reservePriceInfoList = reserveRequest.getReservePriceInfoList();

		int sumOfCount = 0;

		//티켓 정보가 없다.
		if (reservePriceInfoList.size() < 1) {
			return false;
		}

		//티켓 count가 0인 item이 존재한다.
		for (ReservePrice item : reservePriceInfoList) {
			if (item.getCount() < 1) {
				return false;
			}
			sumOfCount += item.getCount();
		}

		//티켓 count의 합이 MAX_TICKET_COUNT를 초과한다.
		if (sumOfCount > CommonProperties.MAX_TICKET_COUNT) {
			return false;
		}

		return ValidationUtils.validateName(reserveRequest.getName())
			&& ValidationUtils.validateTelephone(reserveRequest.getTelephone())
			&& ValidationUtils.validateEmail(reserveRequest.getEmail());
	}

	public static boolean validateReviewWriteRequest(ReviewWriteRequest reviewWriteRequest) {
		MultipartFile imageFile = reviewWriteRequest.getImageFile();
		
		boolean result = ValidationUtils.validateComment(reviewWriteRequest.getComment())
			&& ValidationUtils.validateScore(reviewWriteRequest.getScore());
		
		if (imageFile != null) {
			result = result && ValidationUtils.validateImage(imageFile);
		}
		
		return result;
	}
}