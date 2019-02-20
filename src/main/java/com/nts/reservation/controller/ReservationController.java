package com.nts.reservation.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 예약페이지 url맵핑해주는 클래스
 * @author si yoon
 *
 */
@Controller
@RequestMapping(path = "/reservation")
public class ReservationController {
	/**
	 * 세션에 이메일정보가 있으면 예약정보페이지로, 없으면 로그인페이지로 맵핑
	 * @param session 로그인정보 확인용
	 * @return 뷰이름
	 */
	@GetMapping
	public String myReservation(HttpSession session) {
		String email = (String)session.getAttribute("email");
		if (email == null) {
			return "redirect:login";
		}
		return "myreservation";
	}
}
