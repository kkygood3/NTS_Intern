/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.product.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.display.dto.DisplayResponse;
import com.nts.reservation.display.service.DisplayService;
import com.nts.reservation.product.dto.ImageType;
import com.nts.reservation.product.dto.ProductImage;
import com.nts.reservation.product.dto.ProductResponse;
import com.nts.reservation.product.service.ProductService;

@RestController
@RequestMapping(path = "/api")
public class ProductApiController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductApiController.class);

	@Autowired
	private ProductService productService;

	@Autowired
	private DisplayService displayService;

	private static final int MAX_LIMIT = 20;

	@GetMapping(path = "/products")
	public ProductResponse getProducts(
		@RequestParam(name = "categoryId", required = false, defaultValue = "0") int categoryId,
		@RequestParam(name = "start", required = false, defaultValue = "0") int start,
		@RequestParam(name = "limit", required = false, defaultValue = "4") int limit) {

		if (start < 0 || limit < 0) {
			IllegalArgumentException e = new IllegalArgumentException("Can't use Navgative Value!!!");
			LOGGER.warn("Bad Request! Parameter / start : {} / limit : {}", start, limit, e);
			throw e;
		}

		if (limit > MAX_LIMIT) {
			limit = MAX_LIMIT;
		}

		return productService.getProducts(categoryId, start, limit);
	}

	@GetMapping(path = "/products/{displayInfoId}")
	public DisplayResponse getDisplay(@PathVariable("displayInfoId") int displayInfoId,
		@RequestParam(name = "limit", required = false, defaultValue = "3") int limit) {

		if (displayInfoId < 0) {
			IllegalArgumentException e = new IllegalArgumentException("Can't use Navgative Value!!!"); 
			LOGGER.warn("Bad Request! Parameter / displayInfoId : {}", e.getMessage(), displayInfoId, e);
			throw e;
		}

		if (limit > MAX_LIMIT) {
			limit = MAX_LIMIT;
		}

		return displayService.getDisplayInfo(displayInfoId, limit);
	}

	@GetMapping(path = "/products/etc/{displayInfoId}")
	public ProductImage getProductEtcImage(@PathVariable("displayInfoId") int displayInfoId) {
		List<ProductImage> productImages = productService.getProductImages(displayInfoId, ImageType.ET);

		if (productImages.size() == 0) {
			return new ProductImage();
		}

		return productImages.get(0);
	}
}
