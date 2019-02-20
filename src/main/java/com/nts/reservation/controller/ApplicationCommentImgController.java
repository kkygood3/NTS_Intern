package com.nts.reservation.controller;
/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.common.CommentImage;
import com.nts.reservation.service.CommentService;

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

@RestController
@RequestMapping(path = "/api/")
public class ApplicationCommentImgController {
	@Autowired
	private CommentService commentService;

	@GetMapping(path = "/commentimage/{commentImageId}")
	public String getCommentImageById(HttpServletResponse response,
		@PathVariable(name = "commentImageId", required = true) Long commentImageId) {
		CommentImage image = commentService.getCommentImageById(commentImageId);

		if (image.isDeleteFlag()) {
			return "redirect:index";
		}
		String fileName = image.getFileName();
		String saveFileName = "C:/Users/USER/eclipse-workspace/reservation/" + image.getSaveFileName();
		String contentType = "image/" + FilenameUtils.getExtension(saveFileName);
		Long fileLength = new File(saveFileName).length();

		response.setHeader("Content-Disposition", "inline; filename=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Content-Type", contentType);
		response.setHeader("Content-Length", "" + fileLength);
		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "-1;");

		try (
			FileInputStream fis = new FileInputStream(saveFileName);
			OutputStream out = response.getOutputStream();) {
			int readCount = 0;
			byte[] buffer = new byte[1024];
			while ((readCount = fis.read(buffer)) != -1) {
				out.write(buffer, 0, readCount);
			}
			return "";
		} catch (Exception ex) {
			throw new RuntimeException("file Save Error");
		}
	}
}
