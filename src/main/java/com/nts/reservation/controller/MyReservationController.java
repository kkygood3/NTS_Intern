/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller;

import static com.nts.reservation.constant.ParameterDefaultValue.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.nts.reservation.annotation.PageDefault;
import com.nts.reservation.dto.ProductDto;
import com.nts.reservation.dto.param.PageDto;
import com.nts.reservation.dto.response.MyReservationResponseDto;
import com.nts.reservation.service.ProductService;
import com.nts.reservation.service.ReservationService;

/**
 * 나의 예약 페이지 컨트롤러
 * @author jinwoo.bae
 */
@Controller
public class MyReservationController {
	@Autowired
	private ReservationService reservationService;
	@Autowired
	private ProductService productService;

	@GetMapping("/myreservation")
	public String getMyReservationPage(@PageDefault(limit = RESERVATIONS_LIMIT) PageDto page,
		HttpSession session, Model model) {

		String reservationEmail = (String)session.getAttribute("reservationEmail");

		MyReservationResponseDto myReservationResponse = reservationService.getMyReservationResponse(reservationEmail,
			page);
		model.addAttribute("response", myReservationResponse);

		return "myreservation";
	}

	/**
	 * 상품평 달기 페이지
	 */
	@GetMapping("/myreservation/{reservationId}/comment/write")
	public String getCommentWritePage(@PathVariable int reservationId,
		@RequestParam int productId, Model model) {

		ProductDto product = productService.getProduct(productId);
		model.addAttribute("productDescription", product.getProductDescription());

		return "commentWrite";
	}
}
