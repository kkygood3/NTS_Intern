package com.nts.reservation.reserve.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.display.dao.DisplayDao;
import com.nts.reservation.reserve.dao.ReservationDao;
import com.nts.reservation.reserve.dto.ReservationInfo;
import com.nts.reservation.reserve.dto.ReservationInfoResponse;
import com.nts.reservation.reserve.dto.ReservationParam;
import com.nts.reservation.reserve.dto.ReservationPrice;
import com.nts.reservation.reserve.dto.ReservationResponse;
import com.nts.reservation.reserve.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	ReservationDao reservationDao;
	@Autowired
	DisplayDao displayDao;

	// TODO method 이름 ...ㅠ_ㅠ
	@Override
	public ReservationResponse insertAndReturnRerservationParam(ReservationParam reservationParam) {

		// DB에 저장
		int reservationInfoId = reservationDao.insertReservationInfo(reservationParam);
		reservationParam.getPrice().forEach(price -> {
			price.setReservationInfoId(reservationInfoId);
			reservationDao.insertProductPrice(price);
		});

		return getReservationResponse(reservationInfoId);
	}
	
	@Override
	public ReservationResponse getReservationResponse(int reservationInfoId) {
		List<ReservationPrice> ReservationPrices = reservationDao.selectReservationPrices(reservationInfoId);
		ReservationInfo reservationInfo = reservationDao.selectReservationInfo(reservationInfoId);
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
	
	@Override
	public ReservationInfoResponse getReservationInfoResponse(String reservationEmail) {
		List<ReservationInfo> reservations = reservationDao.selectReservationInfos(reservationEmail);
		reservations.forEach(reservation->{
			reservation.setDisplayInfo(displayDao.selectDisplayInfo(reservation.getDisplayInfoId()));
			reservation.setTotalPrice(reservationDao.selectReservationTotalPrice(reservation.getReservationInfoId()));
		});
		// TODO total price??
		return ReservationInfoResponse.builder()
			.reservations(reservations)
			.size(reservations.size())
			.build();
	}
	
	@Override
	public int cancelReservation(int reservationInfoId) {
		return reservationDao.cancelReservation(reservationInfoId);
	}

}
