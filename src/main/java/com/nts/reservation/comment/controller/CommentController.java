/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.comment.controller;

import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.nts.reservation.imagefile.dto.ImageFile;
import com.nts.reservation.imagefile.service.ImageFileService;
import com.nts.reservation.reserve.dto.ReservationResponse;
import com.nts.reservation.reserve.service.ReservationService;

@Controller
public class CommentController {

	@Autowired
	ReservationService reservationService;
	@Autowired
	ImageFileService imageFileService;

	private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);

	@GetMapping(path = "/review/{displayInfoId}")
	public ModelAndView goReview(@PathVariable(name = "displayInfoId") int displayInfoId, ModelAndView mav) {
		mav.setViewName("review");
		mav.addObject("displayInfoId", displayInfoId);
		return mav;
	}

	@GetMapping(path = "/reservations/{reservationInfoId}/comments")
	public ModelAndView goWriteForm(@PathVariable(name = "reservationInfoId") int reservationInfoId, ModelAndView mav) {
		// session, id 유무
		ReservationResponse reservationResponse = reservationService.getReservationResponse(reservationInfoId);
		mav.addObject(reservationResponse);
		mav.setViewName("reviewWrite");
		return mav;
	}

	@GetMapping("/download/img/{fileId}")
	public ResponseEntity<ImageFile> download(@PathVariable(name = "fileId") int fileId, HttpServletResponse response) {
		ImageFile imageFile = imageFileService.getImageFile(fileId).get(0);
		String fileName = imageFile.getFileName();
		String saveFileName = imageFile.getSaveFileName();
		String contentType = imageFile.getContentType();
		int fileLength = 0;

		HttpHeaders responseHeader = new HttpHeaders();
		responseHeader.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\";");
		responseHeader.add(HttpHeaders.CONTENT_ENCODING, "binary");
		responseHeader.add(HttpHeaders.CONTENT_TYPE, contentType);
		responseHeader.add(HttpHeaders.PRAGMA, "no-cache;");
		responseHeader.add(HttpHeaders.EXPIRES, "-1;");

		try (
			FileInputStream fis = new FileInputStream(saveFileName);
			OutputStream out = response.getOutputStream();) {
			int readCount = 0;
			byte[] buffer = new byte[1024];
			while ((readCount = fis.read(buffer)) != -1) {
				out.write(buffer, 0, readCount);
				fileLength += readCount;
			}
			responseHeader.add(HttpHeaders.CONTENT_LENGTH, "" + fileLength);
		} catch (Exception ex) {
			LOGGER.debug("{}(fileId)번 파일을 읽을 수 없습니다. \t{}", fileId, imageFile);
//			TODO return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			throw new RuntimeException("file Save Error");
		}
		return new ResponseEntity<>(imageFile, responseHeader, HttpStatus.OK);
	}
}
