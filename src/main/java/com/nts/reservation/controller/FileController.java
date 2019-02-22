/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller;

import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
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
	public void showImage(HttpServletResponse response, @PathVariable("fileId") Integer fileId) {
		FileInfo fileInfo = fileIoService.getFileInfo(fileId);

		String fileName = fileInfo.getFileName();
		String saveFileName = fileDir + fileInfo.getSaveFileName();
		String contentType = fileInfo.getContentType();

		response.setHeader("Content-Disposition", "inline; filename=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Content-Type", contentType);
		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "-1;");

		try (
			FileInputStream fileInputStream = new FileInputStream(saveFileName);
			OutputStream outputStream = response.getOutputStream();) {
			int readCount = 0;
			byte[] buffer = new byte[1024];
			while ((readCount = fileInputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, readCount);
			}
		} catch (Exception ex) {
			throw new RuntimeException("file Save Error");
		}
	}
}
