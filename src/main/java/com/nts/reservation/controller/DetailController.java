package com.nts.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nts.reservation.service.FileInfoService;

@Controller
public class DetailController {
	@Autowired
	FileInfoService fileInfoService;
	/**
	 * 상세페이지 URL 맵핑 
	 * @return 메인페이지
	 */
	@GetMapping(path = "/detail")
	public String detail(@RequestParam(name = "product_id", required = true) int productId,
		@RequestParam(name = "display_info_id", required = true) int displayInfoId,
		@RequestParam(name = "description", required = true) String description,
		@RequestParam(name = "place_name", required = true) String placeName,
		@RequestParam(name = "content", required = true) String content,
		ModelMap model) {
		model.addAttribute("productId", productId);
		model.addAttribute("displayInfoId", displayInfoId);
		model.addAttribute("description", description);
		model.addAttribute("placeName", placeName);
		model.addAttribute("content", content);
		model.addAttribute("saveFileName", fileInfoService.getSaveFileNames(productId, "ma"));
		return "detail";

	}
}
