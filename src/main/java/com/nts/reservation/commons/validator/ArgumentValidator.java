/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.commons.validator;

import com.nts.reservation.reservation.dto.ReserveRequest;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public class ArgumentValidator {
	public static boolean checkCategoryId(int categoryId) {
		if (categoryId < 0) {
			String msg = "올바르지 않은 categoryId : " + categoryId;

			System.out.println(msg);
			throw new IllegalArgumentException(msg);
		}
		return true;
	}

	public static boolean checkDisplayInfoId(int displayInfoId) {
		if (displayInfoId <= 0) {
			String msg = "올바르지 않은 displayInfoId : " + displayInfoId;

			System.out.println(msg);
			throw new IllegalArgumentException(msg);
		}
		return true;
	}

	public static boolean checkStartAndLimit(int start, int limit) {
		if (start < 0 || limit < 0) {
			String msg = "올바르지 않은 start : " + start + " 또는 limit" + limit;
			System.out.println(msg);
			throw new IllegalArgumentException(msg);
		}
		return true;
	}

	public static boolean checkCategoryId(int start, int limit) {
		if (start < 0 || limit < 0) {
			String msg = "올바르지 않은 start : " + start + " 또는 limit" + limit;

			System.out.println(msg);
			throw new IllegalArgumentException(msg);
		}
		return true;
	}

	public static boolean checkReserveRequest(ReserveRequest reserveRequest) {
		String reservationName = reserveRequest.getReservationName();
		String reservationTel = reserveRequest.getReservationTel();
		String reservationEmail = reserveRequest.getReservationEmail();
		if (!checkPersonInfo(reservationName, reservationTel, reservationEmail)) {
			return false;
		}

		int displayInfoId = reserveRequest.getDisplayInfoId();
		if (!checkDisplayInfoId(displayInfoId)) {
			return false;
		}

		return true;
	}

	// TO-DO 정규식
	public static boolean checkPersonInfo(String reservationName, String reservationTel, String reservationEmail) {
		return true;
	}

	public static boolean checkReservationId(int reservationInfoId) {
		if (reservationInfoId < 0) {
			String msg = "올바르지 않은 reservationInfoId : " + reservationInfoId;

			System.out.println(msg);
			throw new IllegalArgumentException(msg);
		}
		return true;
	}
}