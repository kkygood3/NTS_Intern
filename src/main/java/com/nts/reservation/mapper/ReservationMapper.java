/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nts.reservation.constant.ReservationStatusType;
import com.nts.reservation.dto.ReservationDisplayInfoDto;
import com.nts.reservation.dto.primitive.ReservationInfoDto;
import com.nts.reservation.dto.primitive.ReservationInfoPriceDto;

/**
 * 예약 Mapper
 * @author jinwoo.bae
 */
@Mapper
public interface ReservationMapper {
	/**
	 * 예약전시정보들을 가져옵니다. state 값에 따라 예약확정, 예약완료, 예약취소 리스트들을 적절히 오름차순해서 가져옵니다.
	 */
	List<ReservationDisplayInfoDto> selectReservationDisplayInfos(@Param("reservationEmail") String reservationEmail,
		@Param("status") ReservationStatusType status, @Param("start") int start, @Param("limit") int limit);
	
	/**
	 * 사용자의 예약상태에따라 개수를 가져옵니다.
	 */
	int selectReservationCountByStatus(@Param("reservationEmail") String reservationEmail, @Param("status") ReservationStatusType status);

	int insertReservationInfo(ReservationInfoDto reservationInfo);

	int insertReservationInfoPrice(ReservationInfoPriceDto reservationInfoPrice);

	/**
	 * 예약 취소
	 */
	void updateReservationToCancel(int reservationId);
}
