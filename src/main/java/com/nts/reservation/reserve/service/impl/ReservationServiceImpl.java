package com.nts.reservation.reserve.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.HttpSessionRequiredException;

import com.nts.reservation.display.dao.DisplayDao;
import com.nts.reservation.reserve.dao.ReservationDao;
import com.nts.reservation.reserve.dto.ReservationInfo;
import com.nts.reservation.reserve.dto.ReservationParam;
import com.nts.reservation.reserve.dto.ReservationPrice;
import com.nts.reservation.reserve.dto.ReservationResponse;
import com.nts.reservation.reserve.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationDao reservationDao;
	@Autowired
	private DisplayDao displayDao;

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);
	private static final int NOT_UPDATED = 0;

	/**
	 * reservationInfoId로 price를 포함한 reservation을 조회 후 반환
	 */
	@Override
	public ReservationResponse getReservationResponse(int reservationInfoId) {
		List<ReservationPrice> ReservationPrices = reservationDao.selectReservationPrices(reservationInfoId);
		ReservationInfo reservationInfo = reservationDao.selectReservationInfo(reservationInfoId);
		reservationInfo.setDisplayInfo(displayDao.selectDisplayInfo(reservationInfo.getDisplayInfoId()));
		return ReservationResponse.builder()
			.reservationInfo(reservationInfo)
			.price(ReservationPrices)
			.build();
	}

	/**
	 * 해당 Email로 예약된 reservation을 반환
	 */
	@Override
	public List<ReservationInfo> getReservationInfoResponse(String reservationEmail) {
		List<ReservationInfo> reservations = reservationDao.selectReservationInfos(reservationEmail);
		reservations.forEach(reservation -> {
			reservation.setDisplayInfo(displayDao.selectDisplayInfo(reservation.getDisplayInfoId()));
			reservation.setTotalPrice(reservationDao.selectReservationTotalPrice(reservation.getReservationInfoId()));
		});

		return reservations;
	}

	/**
	 * 예약 정보의 email과 sesion의 email을 대조 후,
	 * 예약 상태를 취소(cancelYn = true)로 변경(update)
	 */
	@Override
	public ReservationResponse cancelReservation(int reservationInfoId, String sessionEmail)
		throws HttpSessionRequiredException {
		ReservationResponse reservationResponse = getReservationResponse(reservationInfoId);
		String reservationEmail = reservationResponse.getReservationInfo().getReservationEmail();
		if (!reservationEmail.equals(sessionEmail)) {
			HttpSessionRequiredException e = new HttpSessionRequiredException("비정상적 접근 시도!");
			LOGGER.debug("Does not Same email!! / session Email : {} / reservation Email : {}", sessionEmail,
				reservationEmail, e);
			throw e;
		}

		int cancelResult = reservationDao.cancelReservation(reservationInfoId);
		if (cancelResult == NOT_UPDATED) {
			IllegalArgumentException e = new IllegalArgumentException("Does not exist reservationId!!");
			LOGGER.warn("Bad Request! Parameter / reservationId : {}", reservationInfoId, e);
			throw e;
		}
		reservationResponse.getReservationInfo().setCancelYn(true);
		return reservationResponse;
	}

	/**
	 * 입력받은 정보로 새로운 reservation을 등록 후 해당 정보를 반환
	 * @param reservationParam 사용자가 입력한 예약 정보
	 * @return ReservationResponse 완료된 해당 예약 정보
	 */
	@Override
	@Transactional
	public ReservationResponse saveReservation(ReservationParam reservationParam) {
		String reservationTel = reservationParam.getReservationTel();
		reservationParam.setReservationTel(addHyphenToPhone(reservationTel));

		int reservationInfoId = reservationDao.insertReservationInfo(reservationParam);
		reservationParam.getPrice().stream()
			.filter(price -> price.getCount() > 0)
			.forEach(price -> {
				price.setReservationInfoId(reservationInfoId);
				reservationDao.insertReservationPrice(price);
			});

		return getReservationResponse(reservationInfoId);
	}

	/**
	 * 입력받은 전화번호를 양식에 맞게 적용
	 */
	private String addHyphenToPhone(String phone) {
		if (phone.length() == 10) {
			return phone.substring(0, 3) + "-" + phone.substring(3, 6) + "-" + phone.substring(6);
		}
		return phone.substring(0, 3) + "-" + phone.substring(3, 7) + "-" + phone.substring(7);
	}
}
