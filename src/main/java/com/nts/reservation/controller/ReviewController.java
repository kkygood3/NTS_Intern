package com.nts.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nts.reservation.dto.ReviewPageInfo;
import com.nts.reservation.service.ReviewPageInfoService;
@Controller
public class ReviewController {
	@Autowired
	private ReviewPageInfoService reviewPageInfoService;
	
	/**
	 * 상세페이지 URL 맵핑 
	 * @return 메인페이지
	 */
	@GetMapping(path = "detail/{displayInfoId}/review")
	public String review(@PathVariable(name = "displayInfoId", required = true) long displayInfoId,
		ModelMap model) {
		ReviewPageInfo reviewPageInfo = reviewPageInfoService.getReviewPageInfo(displayInfoId);
//		Product product = productService.getProduct(productId);
//		model.addAttribute(DISPLAY_INFO_ID, displayInfoId);
//		model.addAttribute(PRODUCT_ID, productId);
//		model.addAttribute(DESCRIPTION, product.getDescription());
//		model.addAttribute(AVERAGE_SCORE, reservationUserCommentService.getAverageScore(productId));
//		model.addAttribute(COMMENT_COUNT, reservationUserCommentService.getCount(productId));
		
		return "review";
	}
	

}
