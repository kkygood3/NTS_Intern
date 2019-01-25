/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservationservice.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservationservice.dto.DisplayInfoDto;
import com.nts.reservationservice.dto.FileInfoDto;
import com.nts.reservationservice.dto.ProductDto;
import com.nts.reservationservice.dto.ProductImageDto;
import com.nts.reservationservice.dto.PromotionDto;
import com.nts.reservationservice.service.DisplayInfoService;
import com.nts.reservationservice.service.FileInfoService;
import com.nts.reservationservice.service.ProductImageService;
import com.nts.reservationservice.service.ProductService;
import com.nts.reservationservice.service.PromotionService;

@RestController
@RequestMapping(path = "/api/promotions")
public class PromotionApiController {
	@Autowired
	PromotionService promotionService;
	@Autowired
	ProductService productService;
	@Autowired
	DisplayInfoService displayInfoService;
	@Autowired
	ProductImageService productImageService;
	@Autowired
	FileInfoService fileInfoService;

	@GetMapping
	public Map<String, Object> list() {
		Map<String, Object> map = new HashMap();
		List<PromotionDto> list = promotionService.getPromotions();
		List<Map<String, Object>> items = new ArrayList();

		for (PromotionDto promotion : list) {
			ProductDto product = productService.getProduct(promotion.getProductId());
			DisplayInfoDto displayInfo = displayInfoService.getDisplayInfoByProductId(product.getId()).get(0);
			ProductImageDto productImage = productImageService.getProductImageByProductIdAndType(product.getId(), "th");
			FileInfoDto fileInfo = fileInfoService.getFileInfoById(productImage.getFileId());
			Map<String, Object> item = combineDataForGetResult(product, displayInfo, fileInfo);
			items.add(item);
		}

		map.put("items", items);
		map.put("totalCount", list.size());
		return map;
	}

	public Map<String, Object> combineDataForGetResult(ProductDto product, DisplayInfoDto displayInfo,
		FileInfoDto fileInfo) {
		Map<String, Object> map = new HashMap<>();
		map.put("displayInfoId", displayInfo.getId());
		map.put("placeName", displayInfo.getPlaceName());
		map.put("productContent", product.getContent());
		map.put("productDescription", product.getDescription());
		map.put("productId", product.getId());
		map.put("productImageUrl", "../" + fileInfo.getSaveFileName());
		return map;
	}
}
