/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.controller.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.dto.comment.Comments;
import com.nts.service.comment.CommentService;

/**
 * @author 전연빈
 */
@RestController
@RequestMapping("/api/comments")
public class CommentController {

	private final CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	/**
	 * @desc productId 별 comments 불러오기
	 * @param productId
	 * @return comments
	 */
	@RequestMapping("/{productId}")
	public Comments getCommentsByProductId(@PathVariable int productId) {

		return commentService.getComments(productId);
	}
}
