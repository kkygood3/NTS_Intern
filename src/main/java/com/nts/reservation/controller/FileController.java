package com.nts.reservation.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.constant.FilePath;
import com.nts.reservation.dto.primitive.FileInfoDto;
import com.nts.reservation.service.FileService;

/**
 * 각종 파일요청 처리 컨트롤러
 * @author jinwoo.bae
 */
@RestController
public class FileController {
	@Autowired
	private FileService fileService;
	@Autowired
	private ServletContext servletContext;

	/**
	 * 상품평 이미지 다운로드
	 */
	@GetMapping(path = "/img/comments/{id}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
	public byte[] getCommentImage(@PathVariable int id) throws IOException {
		FileInfoDto fileInfo = fileService.getCommentImage(id);

		String saveFileName = FilePath.STATIC_PATH + "/" + fileInfo.getSaveFileName();

		try (InputStream in = servletContext.getResourceAsStream(saveFileName);) {
			return IOUtils.toByteArray(in);
		}
	}

	/**
	 * 이미지 다운로드
	 */
	@GetMapping(path = "/img/**", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE,
		MediaType.IMAGE_GIF_VALUE})
	public byte[] getImage(HttpServletRequest request) throws IOException {
		String saveFileName = FilePath.STATIC_PATH + request.getRequestURI();

		try (InputStream in = servletContext.getResourceAsStream(saveFileName);) {
			return IOUtils.toByteArray(in);
		}
	}

}
