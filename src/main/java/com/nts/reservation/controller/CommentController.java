/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.nts.reservation.model.CommentListInfo;
import com.nts.reservation.service.CommentService;

@RestController
public class CommentController {

	@Autowired
	private CommentService commentService;

	/**
	 * 특정 displayInfoId의 comment 목록 응답 
	 */
	@GetMapping(value = {"/api/products/{displayInfoId}/comments"})
	public CommentListInfo getCommentsInfo(@PathVariable int displayInfoId) {
		return commentService.getCommentListInfo(displayInfoId, CommentService.COUNT_NOT_LIMITED);
	}

}
