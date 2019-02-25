/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.comment.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nts.reservation.comment.model.CommentResponse;
import com.nts.reservation.comment.model.WritedComment;
import com.nts.reservation.comment.service.CommentService;
import com.nts.reservation.common.annotation.MustLogin;
import com.nts.reservation.common.model.Response;

@RestController
public class CommentApiController {

	@Autowired
	private CommentService commentService;

	/**
	 * 특정 displayInfoId의 comment 목록 응답 
	 */
	@GetMapping(value = {"/api/products/{displayInfoId}/comments"})
	public CommentResponse getCommentResponse(@PathVariable int displayInfoId) {
		return new CommentResponse(commentService.getCommentListInfo(displayInfoId, CommentService.COUNT_NOT_LIMITED));
	}

	@MustLogin
	@PostMapping(value = {"/api/comment"})
	public Response addComment(WritedComment writedComment, MultipartFile[] images, HttpSession httpSession) {
		String email = (String)httpSession.getAttribute("email");
		writedComment.setReservationEmail(email);
		commentService.addComment(writedComment, images);
		return new Response();
	}
}
