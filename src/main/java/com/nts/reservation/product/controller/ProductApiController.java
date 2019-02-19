/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.product.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.displayInfo.dto.DisplayInfoResponse;
import com.nts.reservation.displayInfo.service.DisplayInfoService;
import com.nts.reservation.product.dto.ProductExtraImage;
import com.nts.reservation.product.dto.ProductResponse;
import com.nts.reservation.product.service.ProductService;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@RestController
@RequestMapping("/api/products")
public class ProductApiController {
	@Autowired
	private ProductService productServiceImpl;

	@Autowired
	private DisplayInfoService displayInfoServiceImpl;

	private static final String All_CATEGORIES = "0";

	@GetMapping
	public ProductResponse getProductResponse(
		@RequestParam(name = "categoryId", required = false, defaultValue = All_CATEGORIES) int categoryId,
		@RequestParam(name = "start", required = false, defaultValue = "0") int start,
		@RequestParam(name = "limit", required = false, defaultValue = "4") int limit) {

		if (categoryId < 0 || start < 0) {
			System.out.println("올바르지 않은 categoryId : " + categoryId + " 또는 start : " + start);

			throw new IllegalArgumentException("올바르지 않은 categoryId : " + categoryId + " 또는 start : " + start);
		}

		int totalCount = productServiceImpl.getProductsCountByCategoryId(categoryId);
		if (totalCount == 0) {
			return getEmptyItems();
		}

		ProductResponse productResponse = new ProductResponse();
		productResponse.setTotalCount(totalCount);
		productResponse.setItems(productServiceImpl.getProductsByCategoryId(categoryId, start, limit));

		return productResponse;
	}

	@RequestMapping(value = "/{displayInfoId}", method = RequestMethod.GET)
	public DisplayInfoResponse getDisplayInfoResponse(@PathVariable int displayInfoId,
		@RequestParam(name = "start", required = false, defaultValue = "0") int start,
		@RequestParam(name = "limit", required = false, defaultValue = "3") int limit) {

		if (displayInfoId <= 0) {
			System.out.println("올바르지 않은 displayInfoId : " + displayInfoId);

			throw new IllegalArgumentException("올바르지 않은 displayInfoId : " + displayInfoId);
		}
		if (start < 0 || limit < 0) {
			System.out.println("올바르지 않은 start : " + start + " 또는 limit" + limit);

			throw new IllegalArgumentException("올바르지 않은 start : " + start + " 또는 limit" + limit);
		}

		return displayInfoServiceImpl.getDisplayInfoResponse(displayInfoId, start, limit);
	}

	@RequestMapping(value = "/{displayInfoId}/extraImage", method = RequestMethod.GET)
	public Map<String, Object> getProductExtraImage(@PathVariable int displayInfoId) {
		ProductExtraImage productExtraImage = new ProductExtraImage();
		productExtraImage = productServiceImpl.getProductExtraImage(displayInfoId);
		System.out.println(
			"[ProductApiController.java] productExtraImage.getProductImage() : " + productExtraImage.getProductImage());

		Map<String, Object> map = new HashMap<>();
		map.put("productExtraImage", productExtraImage);

		return map;
	}

	private ProductResponse getEmptyItems() {
		ProductResponse productResponse = new ProductResponse();
		productResponse.setTotalCount(0);
		productResponse.setItems(Collections.emptyList());

		return productResponse;
	}
}
