/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller;

import org.hibernate.validator.util.privilegedactions.NewInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.exception.NotFoundDataException;
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

	/**
	 * 특정 diplayInfoId의 display정보, comment정보를 가진 객체를 응답
	 */
	@GetMapping(value = {"/api/products/{displayInfoId}"})
	public ProductDisplayResponse getProductDisplayResponse(@PathVariable int displayInfoId)
		throws NotFoundDataException {

		ProductDisplay productDisplay = productDisplayService.getProductDisplay(displayInfoId);

		if (productDisplay.isEmpty()) {
			throw new NotFoundDataException("productDiplay is Empty");
		}

		CommentListInfo commentListInfo = commentService.getCommentListInfo(displayInfoId,
			CommentService.COUNT_LIMITED);

		return new ProductDisplayResponse(productDisplay, commentListInfo);
	}
}
