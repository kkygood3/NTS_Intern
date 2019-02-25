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

import com.nts.reservation.common.FileUtils;
import com.nts.reservation.dto.download.DownloadInfo;
import com.nts.reservation.service.DownloadService;

/**
 * img나 img_map 요청으로 이미지파일 다운로드 요청
 * @author USER
 */
@Controller
public class DownloadController {
	@Autowired
	DownloadService downloadService;
	
	@GetMapping("/download/{reservationInfoImageId}")
	public void downloadCommentImage(@PathVariable int reservationInfoImageId, HttpServletResponse response) {
		DownloadInfo donwloadResponse = downloadService.downloadImageFile(reservationInfoImageId);
		FileUtils.setDownloadResponse(donwloadResponse, response);
	}
}
