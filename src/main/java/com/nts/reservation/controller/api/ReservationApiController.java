package com.nts.reservation.controller.api;

import static com.nts.reservation.property.Const.DEFAULT_SATRT;
import static com.nts.reservation.property.Const.RESERVATION_DEFAULT_PAGING_SIZE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.nts.reservation.dto.ReservationDisplayItem;
import com.nts.reservation.service.ReservationService;

/**
 * 예약확인 관련 컨트롤러 클래스
 * @author si yoon
 *
 */
@RestController
@RequestMapping(path = "/reservation_info")
public class ReservationApiController {
	@Autowired
	ReservationService reservationService;

	/**
	 * 예약정보 확정, 사용, 취소로 나눠서 start개월전부터 limit개월 가져온다. (default : 1년)
	 * @param session
	 * @param start 시작 날짜 (default : 12개월전)
	 * @param limit 조회할 기간 (default : 12개월)
	 * @return 예약정보
	 */
	@GetMapping
	public Map<String, Object> getMyReservation(@SessionAttribute(name = "email") String email,
		@RequestParam(name = "start", required = false, defaultValue = DEFAULT_SATRT) int start,
		@RequestParam(name = "limit", required = false, defaultValue = RESERVATION_DEFAULT_PAGING_SIZE) int limit) {
		return reservationService.getReservationDisplayItemsByReservationEmail(email, start, limit);
	}
	
	@GetMapping(path="/{type}")
	public Map<String, Object> getMyReservationByType(@SessionAttribute(name = "email") String email,
		@RequestParam(name = "start", required = false, defaultValue = DEFAULT_SATRT) int start,
		@RequestParam(name = "limit", required = false, defaultValue = RESERVATION_DEFAULT_PAGING_SIZE) int limit,
		@PathVariable(name = "type", required = true) String type) {

		Map<String, Object> ReservationDisplayItemListMap = new HashMap<String, Object>();
		ReservationDisplayItemListMap.put("type", type);
		ReservationDisplayItemListMap.put("reservationItems", reservationService.getReservationDisplayItemsByReservationEmailByType(email, start, limit, type));
		return ReservationDisplayItemListMap;
	}

	/**
	 * 예약 취소
	 * @param session 로그인 정보 확인용
	 * @param reservationInfoId 취소할 예약
	 * @return 수정한 라인수. 로그인정보 다르면 -1 리턴
	 */
	@PutMapping(path = "/{reservationInfoId}")
	public int putMyReservation(@SessionAttribute(name = "email") String email,
		@PathVariable(name = "reservationInfoId", required = true) int reservationInfoId) {
		return reservationService.updateCancelFlagToFalseByReservationInfoId(reservationInfoId, email);
	}
}
