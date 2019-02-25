/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nts.reservation.common.FileUtils;
import com.nts.reservation.property.CommonProperties;

/**
 * img나 img_map 요청으로 이미지파일 다운로드 요청
 * @author USER
 */
@Controller
public class DownloadController {
	@GetMapping("/img/{fileName:.+}")
	public void downloadImage(@PathVariable String fileName, HttpServletResponse response) {
		String fileDir = CommonProperties.ROOT_DIR_IMAGE + fileName;
		FileUtils.setResponseHeader(fileName, fileDir, response);
	}

	@GetMapping("/img_map/{fileName:.+}")
	public void downloadImageMap(@PathVariable String fileName, HttpServletResponse response) {
		String fileDir = CommonProperties.ROOT_DIR_IMAGE_MAP + fileName;
		FileUtils.setResponseHeader(fileName, fileDir, response);
	}
}
