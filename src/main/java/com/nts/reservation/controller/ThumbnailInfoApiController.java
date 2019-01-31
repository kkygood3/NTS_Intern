package com.nts.reservation.controller;

import static com.nts.reservation.property.Const.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.ThumbnailInfo;
import com.nts.reservation.service.ProductService;
import com.nts.reservation.service.ThumbnailInfoService;

/*
 * 메인페이지 썸네일 관련 정보 4개씩 리턴
 * @author 시윤
 */
@RestController
@RequestMapping(path = "/thumbnail_info")
public class ThumbnailInfoApiController {
	@Autowired
	private ThumbnailInfoService thumbnailInfoService;

	@Autowired
	private ProductService productService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> list(@RequestParam(name = "start", required = false, defaultValue = "0") int start,
		@RequestParam(name = "category_id", required = false, defaultValue = "0") int categoryId) {
		int productCount = getProductCount(categoryId);
		List<ThumbnailInfo> thumbnailInfoList = Collections.EMPTY_LIST;

		if (productCount > 0) {
			thumbnailInfoList = getThumbnailInfoList(start, categoryId);
		}

		Map<String, Object> map = new HashMap<>();

		map.put("product_count", productCount);
		map.put("thumbnail_info_list", thumbnailInfoList);
		return map;
	}

	private int getProductCount(int categoryId) {
		if (categoryId == ALL_CATEGORIES) {
			return productService.getCount();
		} else {
			return productService.getCount(categoryId);
		}
	}
	private List<ThumbnailInfo> getThumbnailInfoList(int start, int categoryId) {
		List<ThumbnailInfo> ProductList = null;
		if (categoryId == 0) {
			ProductList = thumbnailInfoService.getThumbnailInfos(start);
		} else {
			ProductList = thumbnailInfoService.getThumbnailInfosByCategory(start, categoryId);
		}
		return ProductList;
	}
}
