/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.productdisplay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.comment.model.CommentListInfo;
import com.nts.reservation.comment.service.CommentService;
import com.nts.reservation.common.exception.NotFoundDataException;
import com.nts.reservation.productdisplay.model.ProductDisplay;
import com.nts.reservation.productdisplay.model.ProductDisplayResponse;
import com.nts.reservation.productdisplay.service.ProductDisplayService;

@RestController
public class ProductDisplayApiController {

	@Autowired
	private ProductDisplayService productDisplayService;

	@Autowired
	private CommentService commentService;

	/**
	 * 특정 diplayInfoId의 display정보, comment정보를 가진 객체를 응답
	 */
	@GetMapping(value = {"/api/products/{displayInfoId}"})
	public ProductDisplayResponse getProductDisplayResponse(@PathVariable int displayInfoId) {

		ProductDisplay productDisplay = productDisplayService.getProductDisplay(displayInfoId);

		CommentListInfo commentListInfo = commentService.getCommentListInfo(displayInfoId,
			CommentService.COUNT_LIMITED);

		return new ProductDisplayResponse(productDisplay, commentListInfo);
	}
}
