/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
* @author  : 이승수
*/
@Controller
public class ReviewController {
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
}
