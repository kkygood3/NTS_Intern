package com.nts.reservation.controller;

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
	public Map<String, Object> getProductCountAndThumbnailInfos(@RequestParam(name = "start", required = false, defaultValue = "0") int start,
		@RequestParam(name = "category_id", required = false, defaultValue = "0") int categoryId,
		@RequestParam(name = "limit", required = false, defaultValue = "4") int limit) {
		int productCount = productService.getCount(categoryId);
		List<ThumbnailInfo> thumbnailInfoList = Collections.EMPTY_LIST;

		if (productCount > 0) {
			thumbnailInfoList = thumbnailInfoService.getThumbnailInfos(start, categoryId, limit);
		}

		Map<String, Object> map = new HashMap<>();

		map.put("product_count", productCount);
		map.put("thumbnail_info_list", thumbnailInfoList);
		return map;
	}

}
