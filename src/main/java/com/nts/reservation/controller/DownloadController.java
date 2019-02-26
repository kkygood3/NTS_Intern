/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nts.reservation.common.FileUtils;
import com.nts.reservation.dto.download.DownloadInfo;
import com.nts.reservation.service.DownloadService;

/**
 * 파일 다운로드 요청 Controller
 * @author USER
 */
@Controller
@RequestMapping("/download")
public class DownloadController {
	@Autowired
	DownloadService downloadService;
	
	/**
	 * Comment Image ID로 다운로드 요청
	 * @param reservationInfoImageId
	 * @param response
	 */
	@GetMapping("/{reservationInfoImageId}")
	public void downloadCommentImage(@PathVariable int reservationInfoImageId, HttpServletResponse response) {
		DownloadInfo donwloadResponse = downloadService.downloadImageFile(reservationInfoImageId);
		FileUtils.setDownloadResponse(donwloadResponse, response);
	}
}
