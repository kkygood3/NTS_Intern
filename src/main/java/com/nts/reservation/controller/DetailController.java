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

@Controller
public class DetailController {
	@Autowired
	FileInfoService fileInfoService;
	@Autowired
	ProductService productService;
	@Autowired
	DisplayInfoService displayInfoService;
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
		
		model.addAttribute("productId", productId);
		model.addAttribute("displayInfoId", displayInfoId);
		model.addAttribute("description", product.getDescription());
		model.addAttribute("content", product.getContent());
		model.addAttribute("placeName", displayInfo.getPlaceName());
		model.addAttribute("placeStreet", displayInfo.getPlaceStreet());
		model.addAttribute("placeLot", displayInfo.getPlaceLot());
		model.addAttribute("tel", displayInfo.getTel());
		model.addAttribute("saveFileName", fileInfoService.getSaveFileName(productId, "ma"));
		
		return "detail";
	}
}
