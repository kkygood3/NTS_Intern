/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller;

import static com.nts.reservation.constant.ParameterDefaultValue.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nts.reservation.dto.response.DetailResponseDto;
import com.nts.reservation.service.ProductService;

/**
 * @author jinwoo.bae
 *
 */

@Controller
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductService productService;

	@GetMapping("/{productId}/detail")
	public String getDetailPage(@PathVariable int productId,
		@RequestParam int displayInfoId,
		@RequestParam(required = false, defaultValue = COMMENTS_LIMIT) int commentLimit, Model model) {
		DetailResponseDto detailResponse = productService.getDetailResponse(productId, displayInfoId, commentLimit);
		model.addAttribute("displayInfo", detailResponse.getDisplayInfo());
		model.addAttribute("commentResponse", detailResponse.getCommentResponse());

		return "detail";
	}
}
