/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.comment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nts.reservation.comment.dto.Comment;
import com.nts.reservation.comment.service.CommentService;

@Controller
public class CommentController {

	@Autowired
	private CommentService commentService;

	@GetMapping(path = "/comment")
	public ModelAndView commentPage(@RequestParam(name = "displayInfoId") int displayInfoId) {
		ModelAndView modelAndView = new ModelAndView("review");

		List<Comment> comments = commentService.getComments(displayInfoId);
		double avgScore = commentService.getCommentAvgScore(displayInfoId);
		modelAndView.addObject("comments", comments);
		modelAndView.addObject("avgScore", avgScore);
		return modelAndView;
	}
}
