/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.controller.api.reservation;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nts.dto.displayinfodto.DisplayInfo;
import com.nts.dto.productdto.ProductPrice;
import com.nts.dto.reservationdto.ReservationParam;
import com.nts.dto.reservationinfodto.ReservationDisplayInfo;
import com.nts.dto.reservationinfodto.ReservationInfo;
import com.nts.dto.reservationinfodto.ReservationInfoResponse;
import com.nts.service.DisplayInfoService;
import com.nts.service.ProductService;
import com.nts.service.ReservationService;

/**
*
* @description : Reservation API Controller
* @package : com.nts.controller.api.reservation
* @filename : ProductApiController.java
* @author : 최석현
* @method : ProductResponse products(int categoryId, int start)
* 
*/
@RestController
@RequestMapping(path = "/api/reservations")
public class ReservationApiController {
	@Autowired
	private ReservationService reservationService;

	@Autowired
	private DisplayInfoService displayInfoService;
	
	@Autowired
	private ProductService productService;

	@GetMapping
	public ReservationInfoResponse reservationInfo(HttpSession session) {
		
		String reservationEmail = (String)session.getAttribute("reservationEmail");
		
		ReservationInfoResponse reservationInfoResponse = new ReservationInfoResponse();
		
		List<ReservationInfo> reservationInfos = reservationService.getReservationInfosByReservationEmail(reservationEmail);
		reservationInfoResponse.setReservations(reservationInfos);
		return reservationInfoResponse;
	}
	
	@GetMapping("/{displayInfoId}")
	public ReservationDisplayInfo reservationDisplayInfo(@PathVariable(name = "displayInfoId") int displayInfoId) {
		
		DisplayInfo displayInfo = displayInfoService.getDisplayInfoByDisplayInfoId(displayInfoId);
		List<ProductPrice> reservationPrices = productService.getProductPricesByDisplayInfoId(displayInfoId);
		String productImageUrl = displayInfoService.getProductImageUrlByDisplayInfoId(displayInfoId);
		
		ReservationDisplayInfo reservationDisplayInfo = new ReservationDisplayInfo();
		reservationDisplayInfo.setDisplayInfo(displayInfo);
		reservationDisplayInfo.setReservationPrices(reservationPrices);
		reservationDisplayInfo.setProductImageUrl(productImageUrl);
		
		return reservationDisplayInfo;
	}
	
	@GetMapping("/login")
	public String login(@RequestParam(name = "reservationEmail") String reservationEmail, HttpSession session) {
		session.setAttribute("reservationEmail", reservationEmail);
		session.setMaxInactiveInterval(30 * 60);
		return reservationEmail;
	}
	
	@PostMapping
	public int reservation(@RequestBody ReservationParam reservationParam) {
		return reservationService.addReservation(reservationParam);
	}
	
	@PutMapping("/{reservationInfoId}")
	public void reservationCancel(@PathVariable(name = "reservationInfoId") int reservationInfoId) {
		System.out.println(reservationService.modifyCancelFlagByReservationInfoId(reservationInfoId));
	}
}