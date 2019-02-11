package com.nts.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nts.reservation.dto.DisplayInfo;
import com.nts.reservation.dto.Product;
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
		DisplayInfo displayInfo = displayInfoService.getDisplayInfo(displayInfoId);
		model.addAttribute("product_id", productId);
		model.addAttribute("display_info_id", displayInfoId);
		model.addAttribute("description", product.getDescription());
		model.addAttribute("content", product.getContent());
		model.addAttribute("place_name", displayInfo.getPlaceName());
		model.addAttribute("place_street", displayInfo.getPlaceStreet());
		model.addAttribute("place_lot", displayInfo.getPlaceLot());
		model.addAttribute("tel", displayInfo.getTel());
		model.addAttribute("save_file_name", fileInfoService.getSaveFileName(productId, "ma"));
		model.addAttribute("average_score", reservationUserCommentService.getAverageScore(productId));
		model.addAttribute("comment_count", reservationUserCommentService.getCount(productId));
		
		return "detail";
	}
}
