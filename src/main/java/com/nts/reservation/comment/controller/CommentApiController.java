/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.comment.controller;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.comment.dto.Comment;
import com.nts.reservation.comment.dto.CommentImageDownload;
import com.nts.reservation.comment.service.CommentService;
import com.nts.reservation.commons.validator.NegativeValueValidator;

@RestController
@RequestMapping("/api/comments")
public class CommentApiController {
	private static final String ROOT_DIR_COMMNET_IMAGE = "c:/tmp/img_comment/";

	@Autowired
	private CommentService commentServiceImpl;

	@GetMapping
	public List<Comment> products(
		@RequestParam(name = "id", required = false, defaultValue = "0") int displayInfoId) {

		if(NegativeValueValidator.isNegativeValue(displayInfoId)) {
			throw new IllegalArgumentException("displayInfoId : " + displayInfoId);
		}

		return commentServiceImpl.getTotalComments(displayInfoId);
	}

	@GetMapping("/download/{reservationInfoImageId}")
	public void download(@PathVariable int reservationInfoImageId, HttpServletResponse response) {
		CommentImageDownload donwloadResponse = commentServiceImpl.getCommentImage(reservationInfoImageId);
		int fileLength = 0;
		String fileName = donwloadResponse.getFileName();

		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Content-Type", donwloadResponse.getContentType());
		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "-1;");

		String fileDir = ROOT_DIR_COMMNET_IMAGE + fileName;

		try (FileInputStream fis = new FileInputStream(fileDir);
			OutputStream out = response.getOutputStream();) {
			int readCount = 0;
			byte[] buffer = new byte[1024];
			while ((readCount = fis.read(buffer)) != -1) {
				out.write(buffer, 0, readCount);
				fileLength += readCount;
			}
			response.setHeader("Content-Length", "" + fileLength);
		} catch (Exception ex) {
			throw new RuntimeException("file Load Error");
		}

	}
}
