/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.comment.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.product.dto.ProductExtraImage;
import com.nts.reservation.product.service.ProductService;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@RestController
@RequestMapping("/api/products")
public class DetailCommentApiController {
	@Autowired
	private ProductService productServiceImpl;

	@RequestMapping(value = "/{displayInfoId}/detailComment", method = RequestMethod.GET)
	public Map<String, Object> getProductExtraImage(@PathVariable int displayInfoId) {
		ProductExtraImage productExtraImage = new ProductExtraImage();
		if (productServiceImpl.getProductExtraImage(displayInfoId) == null) {
			//			System.out.println("displayInfoId : " + displayInfoId + " productExtraImage is null");
		} else {
			productExtraImage = productServiceImpl.getProductExtraImage(displayInfoId);
			//			System.out.println("productExtraImage : " + productExtraImage);
		}

		//		System.out.println("displayInfoId : " + displayInfoId + " productExtraImage.getProductImage() : "
		//			+ productExtraImage.getProductImage());

		Map<String, Object> map = new HashMap<>();
		map.put("productExtraImage", productExtraImage);

		return map;
	}
}
