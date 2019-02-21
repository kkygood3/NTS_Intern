package com.nts.reservation.reserve.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	/**
	 * 입력받은 정보로 새로운 reservation을 등록
	 * @param reservationParam
	 * @return auto increment로 생성된 reservationInfoId
	 */
	// XXX update시 반환되는 0, 1값
	@Override
	@Transactional
	public int insertReservation(ReservationParam reservationParam) {
		int reservationInfoId = reservationDao.insertReservationInfo(reservationParam);
		reservationParam.getPrice().stream()
			.filter(price -> price.getCount() > 0)
			.forEach(price -> {
				price.setReservationInfoId(reservationInfoId);
				reservationDao.insertReservationPrice(price);
			});
		
		return reservationInfoId;
	}

	/**
	 * reservationInfoId로 price를 포함한 reservation을 조회 후 반환
	 */
	@Override
	public ReservationResponse getReservationResponse(int reservationInfoId) {
		List<ReservationPrice> ReservationPrices = reservationDao.selectReservationPrices(reservationInfoId);
		ReservationInfo reservationInfo = reservationDao.selectReservationInfo(reservationInfoId);
		// XXX 효율적으로 넣는 법?
		return ReservationResponse.builder()
			.cancelYn(reservationInfo.isCancelYn())
			.createDate(reservationInfo.getCreateDate())
			.displayInfoId(reservationInfo.getDisplayInfoId())
			.modifyDate(reservationInfo.getModifyDate())
			.price(ReservationPrices)
			.productId(reservationInfo.getProductId())
			.reservationDate(reservationInfo.getReservationDate())
			.reservationEmail(reservationInfo.getReservationEmail())
			.reservationInfoId(reservationInfo.getReservationInfoId())
			.reservationName(reservationInfo.getReservationName())
			.reservationTel(reservationInfo.getReservationTel())
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
	 * reservation을 취소
	 */
	@Override
	public int cancelReservation(int reservationInfoId) {
		return reservationDao.cancelReservation(reservationInfoId);
	}

}
