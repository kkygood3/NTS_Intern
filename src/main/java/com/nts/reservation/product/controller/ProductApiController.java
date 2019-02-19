/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.product.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.commons.validator.ArgumentValidator;
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

		ArgumentValidator.isCorrectCategoryId(categoryId);
		ArgumentValidator.isCorrectStartAndLimit(start, limit);

		int totalCount = productServiceImpl.getProductsCountByCategoryId(categoryId);
		if (totalCount == 0) {
			return getEmptyItems();
		}

		return productServiceImpl.getProductResponse(categoryId, start, limit, totalCount);
	}

	@RequestMapping(value = "/{displayInfoId}", method = RequestMethod.GET)
	public DisplayInfoResponse getDisplayInfoResponse(@PathVariable int displayInfoId,
		@RequestParam(name = "start", required = false, defaultValue = "0") int start,
		@RequestParam(name = "limit", required = false, defaultValue = "3") int limit) {

		ArgumentValidator.isCorrectDisplayInfoId(displayInfoId);
		ArgumentValidator.isCorrectStartAndLimit(start, limit);

		return displayInfoServiceImpl.getDisplayInfoResponse(displayInfoId, start, limit);
	}

	@RequestMapping(value = "/{displayInfoId}/extraImage", method = RequestMethod.GET)
	public ProductExtraImage getProductExtraImage(@PathVariable int displayInfoId) {
		ArgumentValidator.isCorrectDisplayInfoId(displayInfoId);

		return productServiceImpl.getProductExtraImage(displayInfoId);
	}

	private ProductResponse getEmptyItems() {
		ProductResponse productResponse = new ProductResponse();
		productResponse.setTotalCount(0);
		productResponse.setItems(Collections.emptyList());

		return productResponse;
	}
}
