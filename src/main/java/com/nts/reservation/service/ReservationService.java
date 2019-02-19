package com.nts.reservation.service;

import java.util.List;
import java.util.Map;

import com.nts.reservation.dto.ReservationDisplayItem;
import com.nts.reservation.dto.ReservationInfo;
import com.nts.reservation.dto.ReservationPageInfo;
import com.nts.reservation.dto.UserReservationInput;

/**
 * 예약관련 서비스 로직 수행하는 클래스
 * @author si yoon
 *
 */
public interface ReservationService {
	/**
	 * 예약페이지에 표시할 정보 가져온다
	 * @param displayInfoId 표시할 상품
	 * @return 예약페이지 정보
	 */
	ReservationPageInfo getReservationPageInfoByDisplayInfoId(long displayInfoId);

	/**
	 * 예약정보 추가
	 * @param userReservationInput 입력받은 예약정보
	 * @param displayInfoId 예약할 상품
	 * @return 예약한 예약정보
	 */
	ReservationInfo addReservation(UserReservationInput userReservationInput, Long displayInfoId);

	/**
	 * 예약 조회 정보 가져온다
	 * @param reservationEmail 조회할 이메일
	 * @param start 시작 (현재부터 start개월 전)
	 * @param limit 기간 (start부터 limit기간)
	 * @return 예약정보
	 */
	Map<String, List<ReservationDisplayItem>> getReservationDisplayItemsByReservationEmail(String reservationEmail, int start, int limit);

	/**
	 * 예약 취소
	 * @param reservationInfoId 취소할 예약
	 * @param reservationEmail 예약자 이메일
	 * @return 취소된 예약 갯수
	 */
	int updateCancelFlagToFalseByReservationInfoId(long reservationInfoId, String reservationEmail);
}
