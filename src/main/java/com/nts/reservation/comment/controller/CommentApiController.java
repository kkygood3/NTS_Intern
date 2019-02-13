/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.comment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.comment.dto.Comment;
import com.nts.reservation.comment.service.impl.CommentServiceImpl;

@RestController
@RequestMapping("/api")
public class CommentApiController {

	@Autowired
	private CommentServiceImpl commentServiceImpl;

	@GetMapping("/comments")
	public List<Comment> products(
		@RequestParam(name = "displayInfoId", required = false, defaultValue = "0") int displayInfoId,
		@RequestParam(name = "count", required = false, defaultValue = "0") int requestedCommentCounts) {


		return commentServiceImpl.getComments(displayInfoId, requestedCommentCounts);
	}
}
