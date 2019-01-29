/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.dto.ProductDto;
import com.nts.service.ProductService;

/**
 * 상품 대한 Web Api 요청을 처리해주는 클래스
 * @author jinwoo.bae
 */
@RestController
@RequestMapping(path = "/api/products")
public class ProductApiController {
	@Autowired
	ProductService productService;

	/**
	 * 상품정보들과 총개수를 가져와 json으로 리턴해준다.
	 */
	@GetMapping
	public Map<String, Object> list(@RequestParam(name = "start", required = false, defaultValue = "0") int start) {
		List<ProductDto> list = productService.getProducts(start);
		int count = productService.getCount();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalCount", count);
		map.put("items", list);

		return map;
	}

	/**
	 * 특정카테고리의 상품들을 start번째 부터 limit 범위만큼 가져와주고 
	 * 해당카테고리에 몇개의 상품이 있는지도 계산하여 가져온후 json으로 리턴해준다.
	 */
	@GetMapping(params = {"categoryId"})
	public Map<String, Object> list(@RequestParam(name = "categoryId", required = true) int categoryId,
		@RequestParam(name = "start", required = false, defaultValue = "0") int start) {
		List<ProductDto> list = productService.getProductsByCategoryId(categoryId, start);
		int count = productService.getCountByCategoryId(categoryId);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalCount", count);
		map.put("items", list);

		return map;
	}
}
