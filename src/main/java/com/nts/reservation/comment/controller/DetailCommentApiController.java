/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.comment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.comment.dto.DetailCommentResponse;
import com.nts.reservation.comment.service.CommentService;
import com.nts.reservation.commons.validator.ArgumentValidator;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@RestController
@RequestMapping("/api/products")
public class DetailCommentApiController {
	@Autowired
	private CommentService commentServiceImpl;

	@GetMapping("/{displayInfoId}/detailComment")
	public DetailCommentResponse getDetailCommentResponse(@PathVariable int displayInfoId,
		@RequestParam(name = "start", required = false, defaultValue = "0") int start,
		@RequestParam(name = "limit", required = false, defaultValue = "3") int limit) {
		ArgumentValidator.checkDisplayInfoId(displayInfoId);

		return commentServiceImpl.getDetailCommentResponse(displayInfoId);
	}
}
