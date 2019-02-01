/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.dto.ProductDto;
import com.nts.service.ProductService;

/**
 * 상품 대한 Web Api 요청을 처리해주는 클래스
 * @author jinwoo.bae
 */
@RestController
public class ProductApiController {
	@Autowired
	private ProductService productService;

	/**
	 * 상품정보들과 총개수를 가져와 json으로 리턴해준다.
	 */
	@GetMapping("/api/products")
	public Map<String, Object> list(@RequestParam int start) {
		int count = productService.getCount();
		List<ProductDto> list = Collections.<ProductDto>emptyList();
		if (count > 0) {
			list = productService.getProducts(start);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalCount", count);
		map.put("items", list);

		return map;
	}

	/**
	 * 특정카테고리의 상품들을 start번째 부터 limit 범위만큼 가져와주고 
	 * 해당카테고리에 몇개의 상품이 있는지도 계산하여 가져온후 json으로 리턴해준다.
	 */
	@GetMapping("/api/categories/{categoryId}/products")
	public Map<String, Object> list(@PathVariable int categoryId, @RequestParam int start) {
		int count = productService.getCountByCategoryId(categoryId);
		List<ProductDto> list = Collections.<ProductDto>emptyList();
		if (count > 0) {
			list = productService.getProductsByCategoryId(categoryId, start);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalCount", count);
		map.put("items", list);

		return map;
	}
}
