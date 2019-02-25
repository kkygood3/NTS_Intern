/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.productdisplay.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.comment.model.CommentListInfo;
import com.nts.reservation.comment.service.CommentService;
import com.nts.reservation.productdisplay.model.ProductDisplay;
import com.nts.reservation.productdisplay.model.ProductDisplayPriceResponse;
import com.nts.reservation.productdisplay.model.ProductDisplayResponse;
import com.nts.reservation.productdisplay.service.ProductDisplayService;
import com.nts.reservation.productprice.model.ProductPrice;
import com.nts.reservation.productprice.service.ProductPriceService;

@RestController
public class ProductDisplayApiController {

	@Autowired
	private ProductDisplayService productDisplayService;

	@Autowired
	private ProductPriceService productPriceService;

	@Autowired
	private CommentService commentService;

	/**
	 * 특정 diplayInfoId의 display정보, comment정보를 가진 객체를 응답
	 */
	@GetMapping(value = {"/api/products/{displayInfoId}"})
	public ProductDisplayResponse getProductDisplayResponse(@PathVariable int displayInfoId) {

		ProductDisplay productDisplay = productDisplayService.getProductDisplay(displayInfoId);

		CommentListInfo commentListInfo = commentService.getCommentListInfo(displayInfoId,
			CommentService.COUNT_LIMITED);

		return new ProductDisplayResponse(productDisplay, commentListInfo);
	}

	/**
	 * product display, price 정보 반환
	 */
	@GetMapping(value = {"/api/product-display-prices/{displayInfoId}"})
	public ProductDisplayPriceResponse getProductDisplayPriceResponse(@PathVariable int displayInfoId) {

		ProductDisplay productDisplay = productDisplayService.getProductDisplay(displayInfoId);
		List<ProductPrice> productPriceList = productPriceService.getProductPriceListByDisplayInfoId(displayInfoId);

		ProductDisplayPriceResponse productDisplayPriceResponse = new ProductDisplayPriceResponse(productDisplay,
			productPriceList);
		productDisplayPriceResponse.setReservationDate(makeReservationDate());
		return productDisplayPriceResponse;
	}

	/**
	 * 오늘 기준 1~5일(random) 이후 날짜 생성
	 */
	private String makeReservationDate() {
		final String yyyyMMdd = "yyyy-MM-dd";
		LocalDate nowDate = LocalDate.now();
		int days = new Random().nextInt(5) + 1;
		return nowDate.plusDays(days).format(DateTimeFormatter.ofPattern(yyyyMMdd));
	}
}
