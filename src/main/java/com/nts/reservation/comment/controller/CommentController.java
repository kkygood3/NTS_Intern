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

import com.nts.reservation.comment.dto.CommentResponse;
import com.nts.reservation.comment.service.CommentService;

@RestController
@RequestMapping(path = "/api")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@GetMapping(path = "/comment/{displayInfoId}")
	public CommentResponse commentPage(@PathVariable("displayInfoId") int displayInfoId,
		@RequestParam(name = "limit", required = false, defaultValue = "0")int limit) {
		return commentService.getComments(displayInfoId, limit);
	}
}
