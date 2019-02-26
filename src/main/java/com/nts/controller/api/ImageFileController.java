/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.controller.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.dto.file.FileInfo;
import com.nts.service.file.FileService;
import com.nts.util.FileSizeUtil;

@RestController
@RequestMapping("/api/images")
public class ImageFileController {
	
	private final FileService fileService;
	
	public ImageFileController(FileService fileService) {
		this.fileService = fileService;
	}
	
	@GetMapping("/{commentId}/commentId")
	public void getImageFile(@PathVariable int commentId, HttpServletResponse response) throws FileNotFoundException{
		FileInfo fileInfo = fileService.getFileInfoByCommentId(commentId);

		File file = new File(fileInfo.getSaveFileName());
        
		try (FileInputStream fis = new FileInputStream(file);
				OutputStream out = response.getOutputStream();) {
			
	        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileInfo.getFileName() + "\";");
	        response.setHeader("Content-Transfer-Encoding", "binary");
	        response.setHeader("Content-Type", fileInfo.getContentType());
	        response.setHeader("Content-Length", "" + file.length());
	        response.setHeader("Pragma", "no-cache;");
	        response.setHeader("Expires", "-1;");
	        
			int readCount = 0;
			byte[] buffer = new byte[FileSizeUtil.KILLO_BYTE];
			
			while ((readCount = fis.read(buffer)) != -1) {
				out.write(buffer, 0, readCount);
			}
			
		} catch (Exception ex) {
			throw new FileNotFoundException("없는 파일 요청입니다.");
		}
	}
}
