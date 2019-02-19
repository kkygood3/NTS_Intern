/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.productprice.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.productdisplay.model.ProductDisplay;
import com.nts.reservation.productdisplay.service.ProductDisplayService;
import com.nts.reservation.productprice.model.ProductPrice;
import com.nts.reservation.productprice.model.ProductPriceResponse;
import com.nts.reservation.productprice.service.ProductPriceService;

@RestController
public class ProductPriceApiController {

	@Autowired
	private ProductDisplayService productDisplayService;

	@Autowired
	private ProductPriceService productPriceService;

	@GetMapping(value = {"/api/product-prices/{displayInfoId}"})
	public ProductPriceResponse getProductPriceResponse(@PathVariable int displayInfoId) {
		ProductDisplay productDisplay = productDisplayService.getProductDisplay(displayInfoId);
		List<ProductPrice> productPriceList = productPriceService.getProductPriceList(displayInfoId);

		ProductPriceResponse productPriceResponse = new ProductPriceResponse(productDisplay, productPriceList);
		productPriceResponse.setReservationDate(makeReservationDate());
		return productPriceResponse;
	}

	private String makeReservationDate() {
		LocalDateTime nowDateTime = LocalDateTime.now();
		int days = new Random().nextInt(5);
		return nowDateTime.plusDays(days).format(DateTimeFormatter.ISO_LOCAL_DATE).replaceAll("-", ".");
	}
}
