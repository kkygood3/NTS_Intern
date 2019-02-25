package com.nts.reservation.controller.api;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.primitive.FileInfoDto;
import com.nts.reservation.service.FileService;

/**
 * 각종 파일요청 처리 컨트롤러
 * 
 * @author jinwoo.bae
 */
@RestController("/api")
public class FileApiController {
	@Autowired
	FileService fileService;

	/**
	 * 상품평 이미지 다운로드
	 */
	@GetMapping(path = "/img/comments/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public byte[] getCommentImage(@PathVariable int id) throws IOException {
		FileInfoDto fileInfo = fileService.getCommentImage(id);

		String saveFileName = "c:/tmp/" + fileInfo.getSaveFileName();

		try (InputStream in = new FileInputStream(saveFileName);) {
			return IOUtils.toByteArray(in);
		}
	}
}
