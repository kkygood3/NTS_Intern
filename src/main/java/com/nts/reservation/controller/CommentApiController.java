package com.nts.reservation.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.comment.CommentImage;
import com.nts.reservation.dto.comment.CommentParam;
import com.nts.reservation.service.CommentService;

/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 * 
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 *
 */

@RestController
@RequestMapping(path = "/api/")
public class CommentApiController {
	@Autowired
	private CommentService commentService;

	@PostMapping(path = "/comment")
	public void postComment(@ModelAttribute CommentParam commentParam) {
		commentService.postComments(commentParam);
	}

	@GetMapping(path = "/commentimage/{commentImageId}")
	public byte[] getCommentImageById(@PathVariable(name = "commentImageId", required = true) Long commentImageId)
		throws IOException {
		CommentImage image = commentService.getCommentImageById(commentImageId);

		if (image.isDeleteFlag()) {
			return null;
		}

		String saveFileName = commentService.basePath + image.getSaveFileName();
		InputStream in = new FileInputStream(new File(saveFileName));
		return IOUtils.toByteArray(in);
	}
}
