/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.commentwrite.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.commentwrite.dto.ReviewWriteRequest;

@RestController
@RequestMapping("/api/commentWrite")
public class CommentWriteApiController {

	@PostMapping()
	public Map<String, Object> registerComment(ReviewWriteRequest reviewWriteRequest) {

		//reviewWriteService.writeReview(reviewWriteRequest);

		return Collections.singletonMap("result", "OK");
	}

}
