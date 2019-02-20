package com.nts.reservation.controller;

import static com.nts.reservation.property.Const.RESERVATION_DEFAULT_LIMIT_MONTH;
import static com.nts.reservation.property.Const.RESERVATION_DEFAULT_START_MONTH;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public Map<String, List<ReservationDisplayItem>> getMyReservation(HttpSession session,
		@RequestParam(name = "start", required = false, defaultValue = RESERVATION_DEFAULT_START_MONTH) int start,
		@RequestParam(name = "limit", required = false, defaultValue = RESERVATION_DEFAULT_LIMIT_MONTH) int limit) {
		String email = (String)session.getAttribute("email");
		if (email == null) {
			return null;
		}
		return reservationService.getReservationDisplayItemsByReservationEmail(email, start, limit);
	}

	/**
	 * 예약 취소
	 * @param session 로그인 정보 확인용
	 * @param reservationInfoId 취소할 예약
	 * @return 수정한 라인수. 로그인정보 다르면 -1 리턴
	 */
	@PutMapping(path = "/{reservationInfoId}")
	public int putMyReservation(HttpSession session,
		@PathVariable(name = "reservationInfoId", required = true) int reservationInfoId) {
		String email = (String)session.getAttribute("email");
		if (email == null || email == "") {
			return -1;
		}
		return reservationService.updateCancelFlagToFalseByReservationInfoId(reservationInfoId, email);
	}
}
