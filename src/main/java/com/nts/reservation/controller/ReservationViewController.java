/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nts.reservation.dto.ReservedItem;
import com.nts.reservation.service.ReservationService;

/**
* @author  : 이승수
*/
@Controller
public class ReservationViewController {
	@Autowired
	ReservationService reservationService;

	@GetMapping("/")
	public String mainPage() {
		return "mainpage";
	}

	@GetMapping("/login")
	public String bookingLoginPage() {
		return "bookinglogin";
	}

	@PostMapping("/loginConfirm")
	public String Login(HttpSession session, @RequestParam(name = "userEmail", required = true) String userEmail) {
		session.setAttribute("userEmail", userEmail);
		return "redirect:/history";
	}

	@GetMapping("/history")
	public String myReservationPage(HttpSession session, ModelMap model) {
		String userEmail = String.valueOf(session.getAttribute("userEmail"));

		List<ReservedItem> availableReservedItems = reservationService.getReservedItems(userEmail, false, false);
		List<ReservedItem> usedReservedItems = reservationService.getReservedItems(userEmail, false, true);
		List<ReservedItem> canceledReservedItems = reservationService.getReservedItems(userEmail, true, false);

		model.put("availableReservedItems", availableReservedItems);
		model.put("usedReservedItems", usedReservedItems);
		model.put("canceledReservedItems", canceledReservedItems);
		return "myreservation";
	}

	@GetMapping("/detail/{displayInfoId}")
	public String detailPage() {
		return "detail";
	}

	@GetMapping("/detail/{displayInfoId}/reviews")
	public String reviewPage() {
		return "review";
	}

	@GetMapping("/detail/{displayInfoId}/reserve")
	public String reservePage(ModelMap model) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, (int)(Math.random() * 5 + 1));
		Date reservationDate = calendar.getTime();

		model.addAttribute("reservationDate", reservationDate);
		return "reserve";
	}
}