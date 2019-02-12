package com.nts.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nts.reservation.dto.Product;
import com.nts.reservation.service.ProductService;
import com.nts.reservation.service.ReservationUserCommentService;
import static com.nts.reservation.property.Const.*;
@Controller
public class reviewController {
	@Autowired
	private ProductService productService;
	@Autowired
	private ReservationUserCommentService reservationUserCommentService;
	
	/**
	 * 상세페이지 URL 맵핑 
	 * @return 메인페이지
	 */
	@GetMapping(path = "/review")
	public String review(@RequestParam(name = "product_id", required = true) long productId,
		@RequestParam(name = "display_info_id", required = true) long displayInfoId,
		ModelMap model) {
		Product product = productService.getProduct(productId);
		model.addAttribute(DISPLAY_INFO_ID, displayInfoId);
		model.addAttribute(PRODUCT_ID, productId);
		model.addAttribute(DESCRIPTION, product.getDescription());
		model.addAttribute(AVERAGE_SCORE, reservationUserCommentService.getAverageScore(productId));
		model.addAttribute(COMMENT_COUNT, reservationUserCommentService.getCount(productId));
		
		return "review";
	}
	

}
