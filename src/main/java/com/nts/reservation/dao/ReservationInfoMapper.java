package com.nts.reservation.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.ReservationDisplayItem;
import com.nts.reservation.dto.ReservationInfo;
import com.nts.reservation.dto.ReservationPageInfo;
import com.nts.reservation.dto.ReservationSummary;

/**
 * 예약관련 DB접근 클래스
 * 
 * @author si yoon
 *
 */
@Repository
public interface ReservationInfoMapper {

	/**
	 * 상품에 맞는 예약페이지화면에 표시할 정보 셀렉트
	 * 
	 * @param displayInfoId 상품정보
	 * @return 예약페이지 화면 정보
	 */
	ReservationPageInfo selectReservationPageInfoByDisplayInfoId(long displayInfoId);

	/**
	 * 예약정보 등록
	 * 
	 * @param reservationInfo 사용자가 입력한 예약정보
	 * @return 예약id
	 */
	int insertReservationInfo(ReservationInfo reservationInfo);

	/**
	 * 해당 email로 예약한 내역 월단위로 페이징해서 셀렉트
	 * 
	 * @param reservationEmail 예약자 이메일
	 * @param start            시작 (현재로부터 start개월 후)
	 * @param end              끝 (현재로부터 end개월 후)
	 * @return 예약정보
	 */
	List<ReservationDisplayItem> selectConfirmedReservationInfoByReservationEmail(
			@Param("reservationEmail") String reservationEmail, @Param("start") int start, @Param("limit") int limit);

	List<ReservationDisplayItem> selectUsedReservationInfoByReservationEmail(
			@Param("reservationEmail") String reservationEmail, @Param("start") int start, @Param("limit") int limit);

	List<ReservationDisplayItem> selectCancelReservationInfoByReservationEmail(
			@Param("reservationEmail") String reservationEmail, @Param("start") int start, @Param("limit") int limit);

	/**
	 * 예약취소
	 * 
	 * @param reservationInfoId 취소할 예약 id
	 * @param reservationEmail  예약한 email
	 * @return 성공여부
	 */
	int updateCancelFlagToFalseByReservationInfoId(@Param("reservationInfoId") long reservationInfoId,
			@Param("reservationEmail") String reservationEmail);

	// TODO: 한번에 여러줄 insert할수있는지 찾아보기
	/**
	 * 예약 가격정보 insert
	 * 
	 * @param price 가격정보
	 * @return 예약 가격 id
	 */
	long insertReservationInfoPrice(Map<String, Object> reservationInfoPrice);

	/**
	 * 특정 사용자 항목별로 예약 카운트 가저온다
	 * 
	 * @param reservationEmail
	 * @return 예약 카운트
	 */
	List<ReservationSummary> selectReservationInfoCountByReservationEmail(String reservationEmail);
}
