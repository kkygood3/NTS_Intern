/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.comment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.comment.dto.Comment;
import com.nts.reservation.comment.service.CommentService;
import com.nts.reservation.commons.validator.NegativeValueValidator;

@RestController
@RequestMapping("/api")
public class CommentApiController {

	@Autowired
	private CommentService commentServiceImpl;

	@RequestMapping(value = "/comments", method=RequestMethod.GET)
	public List<Comment> products(
		@RequestParam(name = "id", required = false, defaultValue = "0") int displayInfoId) {

		if(NegativeValueValidator.isNegativeValue(displayInfoId)) {
			throw new IllegalArgumentException("displayInfoId : " + displayInfoId);
		}

		return commentServiceImpl.getTotalComments(displayInfoId);
	}
}
