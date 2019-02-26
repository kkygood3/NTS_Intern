package com.nts.reservation.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.nts.reservation.service.ReservationService;

/**
 * 예약관련 api 클래스
 * 
 * @author si yoon
 *
 */
@RestController
@RequestMapping("/api/reservation")
public class ReservationApiController {
	@Autowired
	private ReservationService reservationService;

	private static final String DEFAULT_SATRT = "0";
	private static final String RESERVATION_DEFAULT_PAGING_SIZE = "5";

	/**
	 * 예약정보 확정, 사용, 취소로 나눠서 페이징해서 가져온다
	 * 
	 * @param session
	 * @param start   시작인덱스
	 * @param limit   페이징 사이즈
	 * @return 예약정보
	 */
	@GetMapping
	public Map<String, Object> getMyReservation(@SessionAttribute(name = "email") String email,
			@RequestParam(name = "start", required = false, defaultValue = DEFAULT_SATRT) int start,
			@RequestParam(name = "limit", required = false, defaultValue = RESERVATION_DEFAULT_PAGING_SIZE) int limit) {
		return reservationService.getReservationDisplayItemsByReservationEmailWithPaging(email, start, limit);
	}

	/**
	 * 특정 타입 예약정보 start개월전부터 limit개월 가져온다. (default : 1년)
	 * 
	 * @param email
	 * @param start 시작인덱스
	 * @param limit 페이징 사이즈
	 * @param type  { "confirmed", "used", "cancel" }
	 * @return 예약정보
	 */
	@GetMapping("/{status}")
	public Map<String, Object> getMyReservationByType(@SessionAttribute(name = "email") String email,
			@PathVariable(name = "status", required = true) String status,
			@RequestParam(name = "start", required = false, defaultValue = DEFAULT_SATRT) int start,
			@RequestParam(name = "limit", required = false, defaultValue = RESERVATION_DEFAULT_PAGING_SIZE) int limit) {

		Map<String, Object> ReservationDisplayItemListMap = new HashMap<String, Object>();
		ReservationDisplayItemListMap.put("status", status);
		ReservationDisplayItemListMap.put("reservationItems", reservationService
				.getReservationDisplayItemsByReservationEmailByTypeWithPaging(email, start, limit, status));
		return ReservationDisplayItemListMap;
	}

	/**
	 * 예약 취소
	 * 
	 * @param session           로그인 정보 확인용
	 * @param reservationInfoId 취소할 예약
	 * @return 수정한 라인수.
	 */
	@PutMapping("/{reservationInfoId}")
	public int putMyReservation(@SessionAttribute(name = "email") String email,
			@PathVariable(name = "reservationInfoId", required = true) int reservationInfoId) {
		return reservationService.updateCancelFlagToFalseByReservationInfoId(reservationInfoId, email);
	}
}
