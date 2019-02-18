/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.comment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@Controller
public class DetailCommentController {
	@GetMapping("/detailComment")
	public String requestProductDetailComment(
		@RequestParam(name = "displayInfoId", required = true) int displayInfoId) {

		return "detailComment";
	}
}
