/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.dto.displayinfo.DisplayInfos;
import com.nts.dto.product.Products;
import com.nts.exception.DisplayInfoNullException;
import com.nts.exception.ProductParamException;
import com.nts.util.CheckProductParameter;
import com.nts.service.displayInfo.DisplayInfoService;
import com.nts.service.product.ProductService;

/**
 * @author 전연빈
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

	private final ProductService productService;
	private final DisplayInfoService displayInfoService;

	@Autowired
	public ProductController(ProductService productService,DisplayInfoService displayInfoService) {
		this.productService = productService;
		this.displayInfoService = displayInfoService;
	}

	/**
	 * @desc 카테고리별 Product 가져오기
	 * @param categoryId
	 * @param start 
	 * @return products( items [product list] , totalCount [카테고리별 총 개수] )
	 * @throws ProductParamException 
	 */
	@GetMapping
	public Products getProductsByCategory(
		@RequestParam(name = "categoryId", required = false, defaultValue = "0") int categoryId,
		@RequestParam(name = "start", required = true) int start) throws ProductParamException {

		if (CheckProductParameter.isInvalidStart(start)) {
			throw new ProductParamException("start = " + start);
		}

		return productService.getProducts(categoryId, start);
	}
	
	/**
	 * @param displayInfoId
	 * @return displayInfos ()
	 * @throws DisplayInfoNullException
	 */
	@GetMapping("/{displayInfoId}")
	public DisplayInfos getDisplayInfoByProductId(@PathVariable int displayInfoId) throws DisplayInfoNullException {
		
		return displayInfoService.getDisplayInfosByDisplayInfoId(displayInfoId);
	}
}
