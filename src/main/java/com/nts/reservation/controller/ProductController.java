/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller;

import static com.nts.reservation.constant.ParameterDefaultValue.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nts.reservation.constant.ImageType;
import com.nts.reservation.dto.response.DetailResponseDto;
import com.nts.reservation.dto.response.ReserveResponseDto;
import com.nts.reservation.service.ProductService;

/**
 * 프로덕트 관련 페이지 컨트롤러
 * @author jinwoo.bae
 */

@Controller
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductService productService;

	/**
	 * detail 페이지 
	 */
	@GetMapping("/{productId}/detail")
	public String getDetailPage(@PathVariable int productId,
		@RequestParam int displayInfoId,
		@RequestParam(required = false, defaultValue = IMAGE_TYPE_MAIN) String type,
		@RequestParam(required = false, defaultValue = COMMENTS_LIMIT) int commentLimit, Model model) {
		DetailResponseDto detailResponse = productService.getDetailResponse(productId, displayInfoId,
			ImageType.getEnum(type), commentLimit);
		model.addAttribute("displayInfo", detailResponse.getDisplayInfo());
		model.addAttribute("commentResponse", detailResponse.getCommentResponse());
		model.addAttribute("productImageUrl", detailResponse.getProductImageUrl());
		return "detail";
	}

	/**
	 * 예약 페이지
	 */
	@GetMapping("/{productId}/reserve")
	public String getReservePage(@PathVariable int productId,
		@RequestParam int displayInfoId,
		@RequestParam(required = false, defaultValue = IMAGE_TYPE_MAIN) String type, Model model) {
		ReserveResponseDto reserveResponse = productService.getReserveResponse(productId, displayInfoId,
			ImageType.getEnum(type));
		model.addAttribute("displayInfo", reserveResponse.getDisplayInfo());
		model.addAttribute("productPrices", reserveResponse.getProductPrices());
		model.addAttribute("productImageUrl", reserveResponse.getProductImageUrl());
		model.addAttribute("minPrice", reserveResponse.getMinPrice());
		System.out.println(reserveResponse.getReservationDate());
		model.addAttribute("reservationDate", reserveResponse.getReservationDate());
		return "reserve";
	}
}
