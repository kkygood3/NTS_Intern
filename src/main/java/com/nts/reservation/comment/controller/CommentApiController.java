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
import com.nts.reservation.commons.validator.NegativeValueValidator;

@RestController
@RequestMapping("/api")
public class CommentApiController {

	@Autowired
	private CommentServiceImpl commentServiceImpl;

	@GetMapping("/comments")
	public List<Comment> products(
		@RequestParam(name = "displayInfoId", required = false, defaultValue = "0") int displayInfoId,
		@RequestParam(name = "commentUnits", required = false, defaultValue = "0") int commentUnits) {

		if(NegativeValueValidator.isNegativeValue(displayInfoId, commentUnits)) {
			throw new IllegalArgumentException("displayInfoId : " + displayInfoId + ", commentUnits : " + commentUnits);
		}

		return commentServiceImpl.getComments(displayInfoId, commentUnits);
	}
}
