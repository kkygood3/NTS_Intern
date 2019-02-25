/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nts.reservation.dto.FileInfo;
import com.nts.reservation.service.FileIoService;

/**
* @author  : 이승수
*/
@Controller
@PropertySource("classpath:application.properties")
public class FileController {
	@Autowired
	private FileIoService fileIoService;

	@Value("${fileDir}")
	private String fileDir;

	@GetMapping("/reservation/showImage/productId/{productId}")
	public String showImageByProductId(@PathVariable("productId") Integer productId) {
		int fileId = fileIoService.getFileIdByProductId(productId);

		return "redirect:/reservation/showImage/" + fileId;
	}

	@GetMapping("/reservation/showImage/{fileId}")
	public ResponseEntity<byte[]> showImage(@PathVariable("fileId") Integer fileId)
		throws FileNotFoundException, IOException {
		FileInfo fileInfo = fileIoService.getFileInfo(fileId);

		String saveFileName = fileDir + fileInfo.getSaveFileName();

		HttpHeaders headers = new HttpHeaders();
		headers.setCacheControl(CacheControl.noCache().getHeaderValue());

		File file = new File(saveFileName);
		byte[] media = IOUtils.toByteArray(new FileInputStream(file));
		return new ResponseEntity<>(media, headers, HttpStatus.OK);
	}
}
