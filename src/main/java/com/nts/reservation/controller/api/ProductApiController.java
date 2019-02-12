package com.nts.reservation.controller.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.main.MainProduct;
import com.nts.reservation.service.main.MainProductService;

@RestController
public class ProductApiController {
	private final String DEFAULT_PAGING_LIMIT = "4";
	private final String DEFAULT_CATEGORY_ID = "0";
	private final String DEFAULT_START = "0";
	
	@Autowired
	private MainProductService mainProductService;

	/**
	 * /api/products 요청을 받아 메인 페이지에 상품정보를 출력
	 * @param	categoryId	해당 카테고리에 속하는 상품 요청. 0일때 카테고리 구분 없음
	 * @param	start	페이지에 출력할 데이터의 시작 index
	 * @return	JSON text
	 */
	@GetMapping("/api/products")
	public Map<String, Object> products(
		@RequestParam(name = "pagingLimit", required = false, defaultValue = DEFAULT_PAGING_LIMIT) Integer pagingLimit,
		@RequestParam(name = "categoryId", required = false, defaultValue = DEFAULT_CATEGORY_ID) Integer categoryId,
		@RequestParam(name = "start", required = false, defaultValue = DEFAULT_START) Integer start) {

		List<MainProduct> productList = new ArrayList<>();
		int totalCount = mainProductService.getCount(categoryId);

		if (totalCount > 0) {
			productList = mainProductService.getProducts(pagingLimit, categoryId, start);
		}

		Map<String, Object> map = new HashMap<>();
		map.put("productList", productList);
		map.put("totalCount", totalCount);

		return map;
	}
}
