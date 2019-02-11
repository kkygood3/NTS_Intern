package com.nts.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nts.reservation.dto.Product;
import com.nts.reservation.service.CommentDisplayInfoService;
import com.nts.reservation.service.DisplayInfoService;
import com.nts.reservation.service.FileInfoService;
import com.nts.reservation.service.ProductService;
import com.nts.reservation.service.ReservationUserCommentService;

@Controller
public class DetailController {
	@Autowired
	private FileInfoService fileInfoService;
	@Autowired
	private ProductService productService;
	@Autowired
	private DisplayInfoService displayInfoService;
	@Autowired
	private ReservationUserCommentService reservationUserCommentService;
	/**
	 * 상세페이지 URL 맵핑 
	 * @return 메인페이지
	 */
	@GetMapping(path = "/detail")
	public String detail(@RequestParam(name = "product_id", required = true) long productId,
		@RequestParam(name = "display_info_id", required = true) long displayInfoId,
		ModelMap model) {
		Product product = productService.getProduct(productId);
		model.addAttribute("productId", productId);
		model.addAttribute("description", product.getDescription());
		model.addAttribute("content", product.getContent());
		model.addAttribute("saveFileName", fileInfoService.getSaveFileName(productId, "ma"));
		model.addAttribute("averageScore", reservationUserCommentService.getAverageScore(productId));
		model.addAttribute("commentCount", reservationUserCommentService.getCount(productId));
		
		return "detail";
	}
}
