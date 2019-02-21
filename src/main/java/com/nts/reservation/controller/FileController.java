/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.nts.reservation.dto.FileInfo;
import com.nts.reservation.service.FileIoService;

/**
* @author  : 이승수
*/
@Controller
public class FileController {
	@Autowired
	private FileIoService fileIoService;

	@PostMapping("/upload")
	public String upload(@RequestParam("rating") int rating, @RequestParam("review") String review,
		@RequestParam(name = "files", required = false) List<MultipartFile> files) {
		System.out.println("별점 : " + rating);
		System.out.println("리뷰 : " + review);

		if (files != null) {
			for (MultipartFile file : files) {
				System.out.println("파일 이름 : " + file.getOriginalFilename());
				System.out.println("파일 크기 : " + file.getSize());

				try (
					FileOutputStream fileOutputStream = new FileOutputStream("c:/tmp/" + file.getOriginalFilename());
					InputStream inputStream = file.getInputStream();) {
					int readCount = 0;
					byte[] buffer = new byte[1024];
					while ((readCount = inputStream.read(buffer)) != -1) {
						fileOutputStream.write(buffer, 0, readCount);
					}
				} catch (Exception ex) {
					throw new RuntimeException("file Save Error");
				}
			}
		}

		return "redirect:/history";
	}

	@GetMapping("/showImage/productId/{productId}")
	public String showImageByProductId(@PathVariable("productId") Integer productId) {
		int fileId = fileIoService.getFileIdByProductId(productId);

		return "redirect:/showImage/" + fileId;
	}

	@GetMapping("/showImage/displayInfoId/{displayInfoId}")
	public String showImageByDisplayInfoId(@PathVariable("displayInfoId") Integer displayInfoId) {
		int fileId = fileIoService.getFileIdByDisplayInfoId(displayInfoId);

		return "redirect:/showImage/" + fileId;
	}

	@GetMapping("/showImage/{fileId}")
	public void showImage(HttpServletResponse response, @PathVariable("fileId") Integer fileId) {
		FileInfo fileInfo = fileIoService.getFileInfoByProductId(fileId);

		String fileName = fileInfo.getFileName();
		String saveFileName = "C:/2019_1st_intern_reservation/" + fileInfo.getSaveFileName();
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
