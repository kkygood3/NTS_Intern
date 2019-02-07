/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.model.CommentListInfo;
import com.nts.reservation.model.ProductDisplay;
import com.nts.reservation.model.ProductDisplayResponse;
import com.nts.reservation.service.CommentService;
import com.nts.reservation.service.ProductDisplayService;

@RestController
public class ProductDisplayController {

	@Autowired
	private ProductDisplayService productDisplayService;

	@Autowired
	private CommentService commentService;

	@GetMapping(value = {"/api/products/{displayInfoId}"})
	public ProductDisplayResponse getProductDisplayResponse(@PathVariable int displayInfoId) {
		ProductDisplay productDisplay = productDisplayService.getProductDisplay(displayInfoId);
		CommentListInfo commentsInfo = commentService.getCommentListInfo(displayInfoId, CommentService.LIMITED);

		return new ProductDisplayResponse(productDisplay, commentsInfo);
	}
}
