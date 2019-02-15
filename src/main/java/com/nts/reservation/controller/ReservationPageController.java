package com.nts.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nts.reservation.dto.DisplayInfoDto;
import com.nts.reservation.dto.ProductImageDto;
import com.nts.reservation.dto.ProductPriceForRenderDto;
import com.nts.reservation.dto.response.ReservationResponseDto;
import com.nts.reservation.service.DisplayInfoService;

@Controller
public class ReservationPageController {

	@Autowired
	DisplayInfoService displayService;

	/**
	 * 예매하기에 해당하는 페이지 반환
	 * @param displayInfoId
	 * @param map 페이지에 보낼 데이터 맵
	 * @return
	 */
	@GetMapping("/displayInfo/reservation/{displayInfoId}")
	public String reservationPage(@PathVariable Long displayInfoId, ModelMap map) {

		DisplayInfoDto displayInfo = displayService.getDisplayInfo(displayInfoId);
		Long productId = displayInfo.getProductId();
		ProductImageDto productImage = displayService.getProductMainImage(productId);
		List<ProductPriceForRenderDto> productPriceListForRender = displayService
			.getProductPriceForRenderList(productId);

		map.addAttribute("reservationResponseDto",
			new ReservationResponseDto(displayInfo, productImage, productPriceListForRender));

		return "reservationPage";
	}
}
