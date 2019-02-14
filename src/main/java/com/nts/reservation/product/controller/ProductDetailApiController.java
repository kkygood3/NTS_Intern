/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.product.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.comment.dto.Comment;
import com.nts.reservation.comment.dto.CommentImage;
import com.nts.reservation.comment.dto.CommentResponse;
import com.nts.reservation.comment.service.CommentService;
import com.nts.reservation.displayInfo.dto.DisplayInfo;
import com.nts.reservation.displayInfo.dto.DisplayInfoImage;
import com.nts.reservation.displayInfo.dto.DisplayInfoResponse;
import com.nts.reservation.displayInfo.service.DisplayInfoService;
import com.nts.reservation.product.dto.ProductImage;
import com.nts.reservation.product.dto.ProductPrice;
import com.nts.reservation.product.service.ProductService;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@RestController
public class ProductDetailApiController {
	@Autowired
	private ProductService productService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private DisplayInfoService displayInfoService;

	@GetMapping("/api/products/{displayInfoId}")
	public DisplayInfoResponse getItems(@PathVariable int displayInfoId,
		@RequestParam(name = "start", required = false, defaultValue = "0") int start,
		@RequestParam(name = "limit", required = false, defaultValue = "3") int limit) {

		if (isInvalidDisplayInfoId(displayInfoId)) {
			System.out.println("올바르지 않은 displayInfoId");

			return getEmptyDisplayInfoResponse();
		}
		if (isInvalidStartAndLimit(start, limit)) {
			System.out.println("올바르지 않은 start, limit");

			return getEmptyDisplayInfoResponse();
		}

		return getDisplayInfoResponse(displayInfoId, start, limit);
	}

	private boolean isValidDisplayInfoId(int displayInfoId) {
		if (displayInfoId <= 0) {
			return false;
		}
		return true;
	}

	private boolean isInvalidDisplayInfoId(int displayInfoId) {
		return !isValidDisplayInfoId(displayInfoId);
	}

	private boolean isValidStartAndLimit(int start, int limit) {
		if (start < 0 || limit < 0) {
			return false;
		}
		return true;
	}

	private boolean isInvalidStartAndLimit(int start, int limit) {
		return !isValidStartAndLimit(start, limit);
	}

	private List<Comment> getLimitCommentList(int displayInfoId, int start, int limit) {
		List<Comment> commentList = commentService.getLimitCommentByDisplayInfoId(displayInfoId, start, limit);
		List<CommentImage> commentImageList = new ArrayList<CommentImage>();

		for (Comment comment : commentList) {
			commentImageList = commentService.getCommentImageByCommentId(comment.getCommentId());
			comment.setCommentImages(commentImageList);
		}

		CommentResponse commentResponse = new CommentResponse();
		commentResponse.setItems(commentList);

		return commentList;
	}

	private List<Comment> getAllCommentList(int displayInfoId) {
		List<Comment> commentList = commentService.getAllCommentByDisplayInfoId(displayInfoId);
		List<CommentImage> commentImageList = new ArrayList<CommentImage>();

		for (Comment comment : commentList) {
			commentImageList = commentService.getCommentImageByCommentId(comment.getCommentId());
			comment.setCommentImages(commentImageList);
		}

		return commentList;
	}

	private DisplayInfoResponse getEmptyDisplayInfoResponse() {
		DisplayInfoResponse displayInfoResponse = new DisplayInfoResponse();
		displayInfoResponse.setProductImages(Collections.emptyList());
		displayInfoResponse.setProductPrices(Collections.emptyList());
		displayInfoResponse.setComments(Collections.emptyList());
		//displayInfoResponse.setDisplayInfo();
		//displayInfoResponse.setDisplayInfoImage();
		displayInfoResponse.setAverageScore(0.0);

		return displayInfoResponse;
	}

	private DisplayInfoResponse getDisplayInfoResponse(int displayInfoId, int start, int limit) {
		List<ProductImage> productImages = productService.getProductImageByDisplayInfoId(displayInfoId);
		List<ProductPrice> productPrices = productService.getProductPriceByDisplayInfoId(displayInfoId);
		List<Comment> comments = getLimitCommentList(displayInfoId, start, limit); // List<Comment> comments = getAllCommentList(displayInfoId);
		DisplayInfo displayInfo = displayInfoService.getDisplayInfoByDisplayInfoId(displayInfoId);
		DisplayInfoImage displayInfoImage = displayInfoService.getDisplayInfoImageByDisplayInfoId(displayInfoId);
		double averageScore = commentService.getAverageScoreByDisplayInfoId(displayInfoId);

		DisplayInfoResponse displayInfoResponse = new DisplayInfoResponse();

		displayInfoResponse.setProductImages(productImages);
		displayInfoResponse.setProductPrices(productPrices);
		displayInfoResponse.setComments(comments);
		displayInfoResponse.setDisplayInfo(displayInfo);
		displayInfoResponse.setDisplayInfoImage(displayInfoImage);
		displayInfoResponse.setAverageScore(averageScore);

		return displayInfoResponse;
	}
}
