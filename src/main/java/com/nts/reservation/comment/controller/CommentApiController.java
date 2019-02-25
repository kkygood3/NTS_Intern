/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.comment.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.comment.dto.Comment;
import com.nts.reservation.comment.dto.CommentParam;
import com.nts.reservation.comment.dto.CommentResponse;
import com.nts.reservation.comment.service.CommentService;

@RestController
@RequestMapping(path = "/api")
public class CommentApiController {

	@Autowired
	private CommentService commentService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CommentApiController.class);

	private static final int MAX_LIMIT = 50;

	@GetMapping(path = "/comment/{displayInfoId}")
	public CommentResponse commentPage(@PathVariable("displayInfoId") int displayInfoId,
		@RequestParam(name = "limit", required = false, defaultValue = "20") int limit) {

		if (limit > MAX_LIMIT) {
			limit = MAX_LIMIT;
		}

		return commentService.getComments(displayInfoId, limit);
	}

	@PostMapping(path = "/reservations/{reservationInfoId}/comments")
	public ResponseEntity<Comment> writeReview(@PathVariable(name = "reservationInfoId") int reservationInfoId,
		@Valid CommentParam commentParam,
		BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			LOGGER.debug("{} / field : {} / code : {}", bindingResult.getFieldError().getDefaultMessage(),
				bindingResult.getFieldError().getField(), bindingResult.getFieldError().getCode());
			return new ResponseEntity<Comment>(HttpStatus.BAD_REQUEST);
		}

		commentService.insertComment(commentParam);
		// TODO Object 추가
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
