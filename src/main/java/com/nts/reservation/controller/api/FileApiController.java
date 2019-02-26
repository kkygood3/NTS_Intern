package com.nts.reservation.controller.api;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.constant.FilePath;
import com.nts.reservation.dto.primitive.FileInfoDto;
import com.nts.reservation.service.FileService;

/**
 * 각종 파일요청 처리 컨트롤러
 * 
 * @author jinwoo.bae
 */
@RestController
@RequestMapping("/api")
public class FileApiController {
	@Autowired
	FileService fileService;

	/**
	 * 상품평 이미지 다운로드
	 */
	@GetMapping(path = "/img/comments/{id}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
	public byte[] getCommentImage(@PathVariable int id) throws IOException {
		FileInfoDto fileInfo = fileService.getCommentImage(id);

		String saveFileName = FilePath.ROOT_PATH + "/" + fileInfo.getSaveFileName();

		try (InputStream in = new FileInputStream(saveFileName);) {
			return IOUtils.toByteArray(in);
		}
	}

	/**
	 * 이미지 다운로드
	 */
	@GetMapping(path = "/img/**", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
	public byte[] getImage(HttpServletRequest request) throws IOException {
		String saveFileName = FilePath.ROOT_PATH + request.getRequestURI().substring(4); // [/api/img/~] -> [/img/~]

		try (InputStream in = new FileInputStream(saveFileName);) {
			return IOUtils.toByteArray(in);
		}
	}

}
