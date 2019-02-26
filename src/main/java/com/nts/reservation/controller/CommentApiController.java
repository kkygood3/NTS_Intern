package com.nts.reservation.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
	public void postComment(CommentParam commentParam) {
		commentService.postComment(commentParam);
	}

	@GetMapping(path = "/commentimage/{commentImageId}")
	public byte[] getCommentImageById(@PathVariable(name = "commentImageId", required = true) Long commentImageId)
		throws IOException {
		CommentImage image = commentService.getCommentImageById(commentImageId);

		if (image.isDeleteFlag()) {
			throw new RuntimeException("its deleted");
		}

		String saveFileName = commentService.basePath + image.getSaveFileName();
		System.out.println("wdf "+saveFileName);
//		BufferedReader br = new BufferedReader(new InputStreamReader(
//				new FileInputStream(new File(saveFileName)), "UTF-8"));
		InputStream in = new FileInputStream(new File(saveFileName));
		return IOUtils.toByteArray(in);
	}
}
