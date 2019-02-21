/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nts.reservation.dto.ReservationDisplayInfoDto;
import com.nts.reservation.dto.primitive.ReservationInfoDto;
import com.nts.reservation.dto.primitive.ReservationInfoPriceDto;

/**
 * 
 * @author jinwoo.bae
 */
@Mapper
public interface ReservationMapper {
	List<ReservationDisplayInfoDto> selectReservationDisplayInfos(@Param("reservationEmail") String reservationEmail,
		@Param("limit") int limit, @Param("state") String state);
	
	int insertReservationInfo(ReservationInfoDto reservationInfo);
	
	int insertReservationInfoPrice(ReservationInfoPriceDto reservationInfoPrice);

	void updateReservationToCancel(int reservationId);
}
