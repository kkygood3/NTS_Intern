package com.nts.reservation.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nts.reservation.dao.DisplayInfoDao;
import com.nts.reservation.dao.ProductImageDao;
import com.nts.reservation.dao.ProductPriceDao;
import com.nts.reservation.dto.DisplayInfoDto;
import com.nts.reservation.dto.ProductImageDto;
import com.nts.reservation.dto.ProductPriceDto;
import com.nts.reservation.dto.ProductPriceForClientDto;
import com.nts.reservation.dto.response.ReservationResponseDto;

@Controller
public class ReservationPageController {

	@Autowired
	DisplayInfoDao displayInfoDao;

	@Autowired
	ProductImageDao productImageDao;

	@Autowired
	ProductPriceDao productPriceDao;

	/**
	 * 예매하기에 해당하는 페이지 반환
	 * @param displayInfoId
	 * @param map 페이지에 보낼 데이터 맵
	 * @return
	 */
	@GetMapping("/displayInfo/reservation/{displayInfoId}")
	public String reservationPage(@PathVariable Long displayInfoId, ModelMap map) {

		DisplayInfoDto displayInfo = displayInfoDao.selectDisplayInfo(displayInfoId);
		Long productId = displayInfo.getProductId();
		ProductImageDto productImage = productImageDao.selectMainProductImage(productId);
		List<ProductPriceDto> productPriceList = productPriceDao.selectProductPrices(productId);
		List<ProductPriceForClientDto> productPriceForClientList = new ArrayList();

		for (ProductPriceDto price : productPriceList) {
			productPriceForClientList.add(new ProductPriceForClientDto(price));
		}

		map.addAttribute("reservationResponseDto",
			new ReservationResponseDto(displayInfo, productImage, productPriceForClientList));

		return "reservationPage";
	}
}
