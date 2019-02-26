/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.reservation.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.commons.debugPrinter.DebugPrinter;
import com.nts.reservation.commons.validator.ArgumentValidator;
import com.nts.reservation.displayInfo.dao.DisplayInfoDao;
import com.nts.reservation.displayInfo.dto.DisplayInfo;
import com.nts.reservation.reservation.dao.ReservationDao;
import com.nts.reservation.reservation.dao.ReservationPriceDao;
import com.nts.reservation.reservation.dto.ReservationInfo;
import com.nts.reservation.reservation.dto.ReservationInfoResponse;
import com.nts.reservation.reservation.dto.ReservationInfoType;
import com.nts.reservation.reservation.dto.ReservationPrice;
import com.nts.reservation.reservation.dto.ReservationPriceInfo;
import com.nts.reservation.reservation.dto.ReservationPriceType;
import com.nts.reservation.reservation.dto.ReservationResponse;
import com.nts.reservation.reservation.dto.ReserveRequest;
import com.nts.reservation.reservation.service.ReservationService;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@Service
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	ReservationDao reservationDaoImpl;

	@Autowired
	ReservationPriceDao reservationPriceDaoImpl;

	@Autowired
	DisplayInfoDao displayInfoDaoImpl;

	// 예약 조회하기
	@Override
	@Transactional
	public ReservationResponse getReservationResponse(int displayInfoId) {
		ArgumentValidator.checkDisplayInfoId(displayInfoId);

		// ReservationResponse의 필드 ReservationDisplayInfo에 해당
		ReservationResponse reservationResponse = new ReservationResponse();
		reservationResponse.setReservationDisplayInfo(reservationDaoImpl.selectReservationDisplayInfo(displayInfoId));

		List<ReservationPrice> priceList = reservationPriceDaoImpl.selectReservationPrice(displayInfoId);
		if (priceList != null) {
			for (ReservationPrice price : priceList) {
				String typeName = price.getPriceTypeName();
				price.setPriceTypeName(typeName);

				ReservationPriceType typeLabel = iterationFindLabelByName(typeName);
				price.setPriceTypeLabel(typeLabel);
			}
			// ReservationResponse의 필드 List<ReservationPrice>에 해당
			reservationResponse.setPrices(priceList);
		}

		return reservationResponse;
	}

	// 나의 예약 조회하기
	@Override
	public ReservationInfoResponse getReservationInfoResponse(String reservationEmail,
		ReservationInfoType reservationInfoType, int start, int limit) {

		Integer reservationInfoCount = getReservationInfoCount(reservationEmail, reservationInfoType);

		ReservationInfoResponse reservationInfoResponse = new ReservationInfoResponse();

		if (reservationInfoCount != null && reservationInfoCount > start) {
			List<ReservationInfo> reservationInfoList = getReservationInfoList(reservationEmail, reservationInfoType,
				start, limit);

			for (ReservationInfo reservationInfo : reservationInfoList) {
				int displayInfoId = reservationInfo.getDisplayInfoId();

				DisplayInfo displayInfo = displayInfoDaoImpl.selectDisplayInfoByDisplayInfoId(displayInfoId);
				reservationInfo.setDisplayInfo(displayInfo);
			}
			reservationInfoResponse.setReservations(reservationInfoList);
			reservationInfoResponse.setReservationInfoType(reservationInfoType);
			reservationInfoResponse.setCount(reservationInfoList.size());
		}

		return reservationInfoResponse;
	}

	// 예약 하기
	@Override
	@Transactional
	public boolean insertReservationInfo(ReserveRequest reserveRequest) {
		checkReserveRequest(reserveRequest);

		int reservationInfoId = reservationDaoImpl.insertReservationInfo(
			reserveRequest.getReservationName(),
			reserveRequest.getReservationTel(),
			reserveRequest.getReservationEmail(),
			reserveRequest.getDisplayInfoId(),
			reserveRequest.getReservationDate());
		if (reservationInfoId == 0) {
			DebugPrinter.print(Thread.currentThread().getStackTrace()[1],
				"예약 하기 실패\n" + "reservationInfoId : " + reservationInfoId);
			return false;
		}

		int insertCompletePrice = 0;
		List<ReservationPriceInfo> priceInfoList = reserveRequest.getReservationPriceInfoList();
		for (ReservationPriceInfo priceInfo : priceInfoList) {

			ReservationPriceType type = priceInfo.getType();
			String typeName = type.getLabel();
			int count = priceInfo.getCount();
			int displayInfoId = reserveRequest.getDisplayInfoId();

			insertCompletePrice = reservationPriceDaoImpl.insertReservationPrice(reservationInfoId, typeName, count,
				displayInfoId);
			if (insertCompletePrice == 0) {
				DebugPrinter.print(Thread.currentThread().getStackTrace()[1],
					"예약 하기 실패\n" + "insertCompletePrice : " + insertCompletePrice);
				return false;
			}
		}

		return true;
	}

	// 나의 예약 취소하기
	@Override
	public boolean cancelReservation(int reservationInfoId) {
		checkReservationId(reservationInfoId);

		int cancelCompleteCount = reservationDaoImpl.cancelReservation(reservationInfoId);
		if (cancelCompleteCount == 0) {
			return false;
		}
		return true;
	}

	// 예정, 완료, 취소에 해당하는 예매의 수
	private Integer getReservationInfoCount(String reservationEmail, ReservationInfoType reservationInfoType) {
		Integer reservationInfoCount = null;

		if (reservationInfoType.name().equals("CONFIRM")) {
			reservationInfoCount = reservationDaoImpl.selectConfirmReservationInfoCount(reservationEmail);
		} else if (reservationInfoType.name().equals("COMPLETE")) {
			reservationInfoCount = reservationDaoImpl.selectCompleteReservationInfoCount(reservationEmail);
		} else if (reservationInfoType.name().equals("CANCEL")) {
			reservationInfoCount = reservationDaoImpl.selectCancelReservationInfoCount(reservationEmail);
		}

		return reservationInfoCount;
	}

	// 예정, 완료, 취소에 해당하는 예매 목록
	private List<ReservationInfo> getReservationInfoList(String reservationEmail,
		ReservationInfoType reservationInfoType, int start, int limit) {
		List<ReservationInfo> reservationInfoList = new ArrayList();

		if (reservationInfoType.name().equals("CONFIRM")) {
			reservationInfoList = reservationDaoImpl.selectConfirmReservationInfo(reservationEmail, start, limit);
		} else if (reservationInfoType.name().equals("COMPLETE")) {
			reservationInfoList = reservationDaoImpl.selectCompleteReservationInfo(reservationEmail, start, limit);
		} else if (reservationInfoType.name().equals("CANCEL")) {
			reservationInfoList = reservationDaoImpl.selectCancelReservationInfo(reservationEmail, start, limit);
		}

		return reservationInfoList;
	}

	private static boolean checkReservationId(int reservationInfoId) {
		if (reservationInfoId < 0) {
			String msg = "올바르지 않은 reservationInfoId : " + reservationInfoId;

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

	private static boolean checkReserveRequest(ReserveRequest reserveRequest) {
		String reservationName = reserveRequest.getReservationName();
		String reservationTel = reserveRequest.getReservationTel();
		String reservationEmail = reserveRequest.getReservationEmail();
		if (!checkPersonInfo(reservationName, reservationTel, reservationEmail)) {
			return false;
		}

		int displayInfoId = reserveRequest.getDisplayInfoId();
		if (!ArgumentValidator.checkDisplayInfoId(displayInfoId)) {
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

	private static boolean checkPersonInfo(String reservationName, String reservationTel, String reservationEmail) {
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

	private static boolean checkName(String reservationName) {
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

	private static boolean checkTelephone(String reservationTel) {
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

	private static boolean checkEmail(String reservationEmail) {
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

	private ReservationPriceType iterationFindLabelByName(String typeName) {
		for (ReservationPriceType priceType : ReservationPriceType.values()) {
			if (typeName.equals(priceType.getLabel())) {
				return priceType;
			}
		}
		return null;
	}
}
