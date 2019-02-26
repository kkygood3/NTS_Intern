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
import org.springframework.web.bind.annotation.PathVariable;

import com.nts.reservation.dto.DisplayInfo;
import com.nts.reservation.dto.ReservedItem;
import com.nts.reservation.service.DetailProductService;
import com.nts.reservation.service.ReservationService;

/**
* @author  : 이승수
*/
@Controller
public class ReservationViewController {
	@Autowired
	private ReservationService reservationService;
	@Autowired
	private DetailProductService detailProductService;

	@GetMapping("/reservation/history")
	public String getMyReservationPage(HttpSession session, ModelMap model) {
		String userEmail = String.valueOf(session.getAttribute("userEmail"));

		List<ReservedItem> reservedItems = reservationService.getReservedItems(userEmail);

		model.put("reservedItems", reservedItems);
		return "myreservation";
	}

	@GetMapping("/displayInfo/detail/{displayInfoId}/reserve")
	public String getReservePage(ModelMap model) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, (int)(Math.random() * 5 + 1));
		Date reservationDate = calendar.getTime();

		model.addAttribute("reservationDate", reservationDate);
		return "reserve";
	}

	@GetMapping("/reservation/reviewWrite/{reservationInfoId}")
	public String getReviewWritePage(@PathVariable("reservationInfoId") int reservationInfoId, HttpSession session,
		ModelMap model) {
		String userEmail = String.valueOf(session.getAttribute("userEmail"));
		if (!reservationService.isExistReservationInfo(reservationInfoId, userEmail)) {
			return "redirect:/reservation/history";
		}

		DisplayInfo displayInfo = detailProductService
			.getDisplayInfo(reservationService.getDisplayInfoId(reservationInfoId));
		model.put("productDescription", displayInfo.getProductDescription());
		model.put("productId", displayInfo.getProductId());

		return "reviewWrite";
	}
}