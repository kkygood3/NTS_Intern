/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.product.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.comment.dto.CommentResponse;
import com.nts.reservation.comment.service.CommentService;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@RestController
public class ProductDetailApiController {
	@Autowired
	private CommentService commentService;

	@GetMapping("/api/{displayInfoId}")
	public CommentResponse getItems(
		@RequestParam(name = "productId", required = false, defaultValue = "0") int productId) {

		if (isInvalidParameter(productId)) {
			System.out.println("올바르지 않은 categoryId 또는 start");

			return getEmptyitems();
		}

		CommentResponse commentResponse = new CommentResponse();
		commentResponse.setItems(commentService.getCommentByProductId(productId));

		return commentResponse;
	}

	private boolean isValidParameter(int productId) {
		if (productId <= 0) {
			return false;
		}
		return true;
	}

	private boolean isInvalidParameter(int productId) {
		return !isValidParameter(productId);
	}

	private CommentResponse getEmptyitems() {
		CommentResponse commentResponse = new CommentResponse();
		commentResponse.setItems(Collections.emptyList());

		return commentResponse;
	}
}
