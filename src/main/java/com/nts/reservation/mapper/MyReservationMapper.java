package com.nts.reservation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nts.reservation.dto.myreservation.MyReservationInfo;

public interface MyReservationMapper {
	public List<MyReservationInfo> selectMyReservation(
		@Param("email") String email,
		@Param("type") String type,
		@Param("start") int start,
		@Param("pagingLimit") int pagingLimit);
	
	public Integer selectMyReservationCount(
		@Param("email") String email,
		@Param("type") String type);
}
