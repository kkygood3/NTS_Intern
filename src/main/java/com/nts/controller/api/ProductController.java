/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.exception.ProductParamException;
import com.nts.layer.product.dto.ProductResponse;
import com.nts.layer.product.service.ProductService;

/**
 * @author 전연빈
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;

	/**
	 * @desc 카테고리별 Product 가져오기
	 * @param categoryId
	 * @param start 
	 * @return productResponse( items [product list] , totalCount [카테고리별 총 개수] )
	 * @throws ProductParamException 
	 */
	@GetMapping
	public ProductResponse getProductsByCategory(@RequestParam(name = "categoryId", required = false, defaultValue= "0")int categoryId,
		@RequestParam(name = "start", required = true)int start) throws ProductParamException  {
		
		try {
			return productService.getProductsByCategory(categoryId, start);
		} catch(ProductParamException e) {
			throw new ProductParamException();
		}
	}
}
