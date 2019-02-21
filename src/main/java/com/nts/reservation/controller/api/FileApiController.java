/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.controller.api;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.FileDto;
import com.nts.reservation.exception.CustomFileNotFoundException;
import com.nts.reservation.service.FileIoService;
import com.nts.reservation.service.ReservationService;

/**
 * @author 육성렬
 */
@RestController
@RequestMapping(path = "/api")
public class FileApiController {

	@Autowired
	private FileIoService fileIoService;

	@Autowired
	private ReservationService reservationService;

	@GetMapping("/download/img")
	public void getDownloadFile(@RequestParam(required = true) String imageName, HttpServletResponse response)
		throws IOException, CustomFileNotFoundException {
		fileIoService.sendFile("/" + imageName, response.getOutputStream());
	}

	@GetMapping("/comment/image/download/{commentImageId}")
	public void getCommentImageFile(@PathVariable Long commentImageId, HttpServletResponse response)
		throws IOException, CustomFileNotFoundException {
		FileDto file = reservationService.getFileByCommentImageId(commentImageId);
		if (file == null) {
			throw new CustomFileNotFoundException("CommentImageId", commentImageId);
		}
		String imagePath = "/" + file.getSaveFileName();
		fileIoService.sendFile(imagePath, response.getOutputStream());
	}
}
