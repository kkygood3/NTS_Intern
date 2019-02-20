/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.controller.api.reservation;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nts.dto.displayinfodto.DisplayInfo;
import com.nts.dto.productdto.ProductPrice;
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
	
	private ObjectMapper mapper = new ObjectMapper();
	

	@GetMapping
	public ModelAndView reservationInfo(@RequestParam(name="reservationEmail") String reservationEmail,
			HttpSession session) throws JsonProcessingException {
		if(session.getAttribute(reservationEmail) == null) {
			session.setAttribute("reservationEmail", reservationEmail);
		}
		
		ReservationInfoResponse reservationInfoResponse = new ReservationInfoResponse();
		
		List<ReservationInfo> reservationInfos = reservationService.getReservationInfosByReservationEmail(reservationEmail);
		reservationInfoResponse.setReservations(reservationInfos);
		
		ModelAndView mav = new ModelAndView("/myreservation");
		mav.addObject("reservationInfo", mapper.writeValueAsString(reservationInfoResponse));
		
		return mav;
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
	
	@PostMapping
	void reservation() {
		
	}
	
	@PutMapping("/{reservationInfoId}")
	public void reservationCancel(@PathVariable(name = "reservationInfoId") int reservationInfoId) {
		reservationService.modifyCancelFlagByReservationInfoId(reservationInfoId);
	}
}