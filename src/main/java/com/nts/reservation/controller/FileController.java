package com.nts.reservation.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nts.reservation.dto.FileInfo;
import com.nts.reservation.service.FileService;
import com.nts.reservation.util.FileHandler;

/**
 * 파일관련 요청 처리하는 클래스
 * @author si yoon
 *
 */
@Controller
@RequestMapping("file")
public class FileController {
	@Autowired
	private FileService fileService;

	/**
	 * 리뷰 이미지 다운로드
	 * @param response 응답객체
	 * @param commentImageId 리뷰이미지 정보
	 */
	@GetMapping("/comment/{commentImageId}")
	public void download(HttpServletResponse response,
		@PathVariable(name = "commentImageId", required = true) long commentImageId) {
		FileInfo imageInfo = fileService.getFileInfoByReservationUserCommentImageId(commentImageId);
		String saveFileName = FileHandler.getFullPath(imageInfo.getSaveFileName());

		response.setHeader("Content-Disposition", "attachment; filename=\"" + imageInfo.getFileName() + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Content-Type", imageInfo.getContentType());
		response.setHeader("Content-Length", "" + new File(saveFileName).length());
		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "-1;");
		
		try (FileInputStream fis = new FileInputStream(saveFileName);
			OutputStream out = response.getOutputStream();) {
			int readCount = 0;
			
			byte[] buffer = new byte[1024];
			while ((readCount = fis.read(buffer)) != -1) {
				out.write(buffer, 0, readCount);
			}
		} catch (Exception ex) {
			throw new RuntimeException("file download Error");
		}
	}
}
