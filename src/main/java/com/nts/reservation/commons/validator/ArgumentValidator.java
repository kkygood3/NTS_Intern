/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.commons.validator;

import java.util.List;
import java.util.regex.Pattern;

import com.nts.reservation.reservation.dto.ReservationPriceInfo;
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

	private static final String REGULAR_KOREAN_NAME = "^[가-힣]{2,10}$";
	private static final String REGULAR_TELEPHONE = "^\\d{2,3}-\\d{3,4}-\\d{4}$";
	private static final String REGULAR_EMAIL = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";

	private static final int MAX_NAME_LENGTH = 10;
	private static final int MAX_TELEPHONE_LENGTH = 13;
	private static final int MAX_EMAIL_LENGTH = 50;

	private static final int MAX_TICKET_COUNT = 10;

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

		int ticketCount = 0;
		List<ReservationPriceInfo> priceInfoList = reserveRequest.getReservationPriceInfoList();
		for (ReservationPriceInfo priceInfo : priceInfoList) {
			ticketCount = ticketCount + priceInfo.getCount();
		}
		if (ticketCount > MAX_TICKET_COUNT) {
			return false;
		}

		return true;
	}

	public static boolean checkPersonInfo(String reservationName, String reservationTel, String reservationEmail) {
		if (!checkName(reservationName)) {
			return false;
		}
		if (!checkTelephone(reservationTel)) {
			return false;
		}
		if (!checkEmail(reservationEmail)) {
			return false;
		}
		return true;
	}

	public static boolean checkName(String reservationName) {
		if (reservationName == null) {
			return false;
		}
		if (reservationName.length() > MAX_NAME_LENGTH) {
			return false;
		}
		if (!Pattern.matches(REGULAR_KOREAN_NAME, reservationName.trim())) {
			return false;
		}
		return true;
	}

	public static boolean checkTelephone(String reservationTel) {
		if (reservationTel == null) {
			return false;
		}
		if (reservationTel.length() > MAX_TELEPHONE_LENGTH) {
			return false;
		}
		if (!Pattern.matches(REGULAR_TELEPHONE, reservationTel.trim())) {
			return false;
		}
		return true;
	}

	public static boolean checkEmail(String reservationEmail) {
		if (reservationEmail == null) {
			return false;
		}
		if (reservationEmail.length() > MAX_EMAIL_LENGTH) {
			return false;
		}
		if (!Pattern.matches(REGULAR_EMAIL, reservationEmail.trim())) {
			return false;
		}
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