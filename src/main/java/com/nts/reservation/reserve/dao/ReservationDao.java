package com.nts.reservation.reserve.dao;

import java.util.List;

import com.nts.reservation.reserve.dto.ReservationInfo;
import com.nts.reservation.reserve.dto.ReservationParam;
import com.nts.reservation.reserve.dto.ReservationPrice;

public interface ReservationDao {
	int insertReservationInfo(ReservationParam reservationParam);

	int insertReservationPrice(ReservationPrice reservationPrice);

	List<ReservationInfo> selectReservationInfos(String reservationEmail);

	ReservationInfo selectReservationInfo(int reservationInfoId);

	List<ReservationPrice> selectReservationPrices(int reservationInfoId);

	int selectReservationTotalPrice(int reservationInfoId);

	int cancelReservation(int reservationInfoId);
}
