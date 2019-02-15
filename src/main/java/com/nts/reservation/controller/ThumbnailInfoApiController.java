package com.nts.reservation.controller;

import static com.nts.reservation.property.Const.DEFAULT_SATRT;
import static com.nts.reservation.property.Const.SELECT_ALL;
import static com.nts.reservation.property.Const.THUMBNAIL_DEFAULT_PAGING_SIZE;

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

/**
 * 썸네일 관련 API 클래스
 * @author USER
 *
 */
@RestController
@RequestMapping(path = "/thumbnail_info")
public class ThumbnailInfoApiController {
	@Autowired
	private ThumbnailInfoService thumbnailInfoService;

	@Autowired
	private ProductService productService;

	/**
	 * 썸네일 정보 start부터 limit개 리턴
	 * 
	 * @param start 시작 인덱스, 지정하지않으면 0
	 * @param limit SELECT할 썸네일 갯수, 지정하지않으면 4
	 * @param categoryId SELECT할 카테고리, 지정하지않으면 전체 카테고리
	 * @return 해당카테고리의 전체 상품 갯수와 limit개의 썸네일 정보
	 */
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> getProductCountAndThumbnailInfos(
		@RequestParam(name = "start", required = false, defaultValue = DEFAULT_SATRT) int start,
		@RequestParam(name = "limit", required = false, defaultValue = THUMBNAIL_DEFAULT_PAGING_SIZE) int limit,
		@RequestParam(name = "category_id", required = false, defaultValue = SELECT_ALL) int categoryId) {
		int productCount = productService.getCount(categoryId);
		List<ThumbnailInfo> thumbnailInfoList = null;

		if (productCount > 0) {
			thumbnailInfoList = thumbnailInfoService.getThumbnailInfos(categoryId, start, limit);
		}

		Map<String, Object> map = new HashMap<>();
		map.put("productCount", productCount);
		map.put("thumbnailInfoList", thumbnailInfoList);
		return map;
	}
}
