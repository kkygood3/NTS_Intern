/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.controller.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.FileDto;
import com.nts.reservation.exception.CustomFileNotFoundException;
import com.nts.reservation.service.ReservationService;

/**
 * @author 육성렬
 */
@RestController
@RequestMapping(path = "/api/download")
@PropertySource("classpath:application.properties")
public class FileApiController {

	private final ReservationService reservationService;

	@Value("${fileDir}")
	private String fileDir;

	@Autowired
	public FileApiController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@GetMapping("/img")
	public ResponseEntity<byte[]> getDownloadFile(@RequestParam(required = true) String imageName,
		HttpServletResponse response)
		throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.setCacheControl(CacheControl.noCache().getHeaderValue());
		return getImageAsResponseEntity("/" + imageName, headers);

	}

	@GetMapping("/comment/image/{commentImageId}")
	public ResponseEntity<byte[]> getCommentImageFile(@PathVariable Long commentImageId, HttpServletResponse response)
		throws IOException, CustomFileNotFoundException {
		FileDto file = reservationService.getFileByCommentImageId(commentImageId);
		if (file == null) {
			throw new CustomFileNotFoundException("CommentImageId", commentImageId);
		}

		String imagePath = "/" + file.getSaveFileName();
		HttpHeaders headers = new HttpHeaders();
		headers.setCacheControl(CacheControl.noCache().getHeaderValue());
		return getImageAsResponseEntity(imagePath, headers);
	}

	private ResponseEntity<byte[]> getImageAsResponseEntity(String filePath, HttpHeaders headers)
		throws IOException {
		File file = new File(fileDir + filePath);
		byte[] media = IOUtils.toByteArray(new FileInputStream(file));
		return new ResponseEntity<>(media, headers, HttpStatus.OK);
	}
}
