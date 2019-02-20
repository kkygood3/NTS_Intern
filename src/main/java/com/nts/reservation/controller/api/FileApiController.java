/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.controller.api;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.service.FileIoService;

/**
 * @author 육성렬
 */
@RestController
@RequestMapping(path = "/api")
public class FileApiController {

	@Autowired
	FileIoService fileIoService;

	@GetMapping("/download/**")
	public void getDownloadFile(HttpServletResponse response, HttpServletRequest request)
		throws IOException {
		String imagePath = request.getServletPath().replaceFirst("^.*\\/download", "");
		fileIoService.sendFile(imagePath, response.getOutputStream());
	}
}
