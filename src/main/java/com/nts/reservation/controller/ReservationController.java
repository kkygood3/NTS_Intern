package com.nts.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nts.reservation.dto.ReservationPageInfo;
import com.nts.reservation.service.ReservationService;

/**
 * 예약페이지 url맵핑해주는 클래스
 * @author si yoon
 *
 */
@Controller
@RequestMapping(path="reservation")
public class ReservationController {

	
	@GetMapping
	public String myReservation() {
		return "myreservation";
	}
}
