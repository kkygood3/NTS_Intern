package com.nts.reservation.controller;

import static com.nts.reservation.property.Const.RESERVATION_DEFAULT_LIMIT_MONTH;
import static com.nts.reservation.property.Const.RESERVATION_DEFAULT_START_MONTH;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.ReservationDisplayItem;
import com.nts.reservation.service.ReservationService;

@RestController
@RequestMapping(path="/reservation_info")
public class ReservationApiController {
	@Autowired
	ReservationService reservationService;
	@GetMapping
	public List<List<ReservationDisplayItem>> myReservation(HttpSession session,
		@RequestParam(name="start", required=false, defaultValue = RESERVATION_DEFAULT_START_MONTH) int start,
		@RequestParam(name="limit", required=false, defaultValue = RESERVATION_DEFAULT_LIMIT_MONTH) int limit) {
		String email = (String) session.getAttribute("email");
		if (email == null) {
			return null;
		}
		return reservationService.getReservationDisplayItemsByReservationEmail(email, start, limit);
	}
}
