/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.controller.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.dto.comment.Comments;
import com.nts.dto.file.FileInfo;
import com.nts.exception.FileNotFoundException;
import com.nts.service.comment.CommentService;

/**
 * @author 전연빈
 */
@RestController
@RequestMapping("/api/comments")
public class CommentController {

	private static final String FILE_PATH = "/Users/USER/git/2019_1st_intern6/src/main/webapp/";
	
	private final CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	/**
	 * @desc productId 별 comments 불러오기
	 * @param productId
	 * @return comments
	 */
	@RequestMapping("/{productId}")
	public Comments getCommentsByProductId(@PathVariable int productId) {

		return commentService.getComments(productId);
	}
	
	/**
	 * @desc commentId별 이미지 전송
	 * @param commentId
	 * @param response
	 * @throws FileNotFoundException
	 */
	@RequestMapping("/{commentId}/commentId")
	public void getCommentImageByCommentId(@PathVariable int commentId, HttpServletResponse response) throws FileNotFoundException {
		
		FileInfo fileInfo = commentService.getFileInfoByCommentId(commentId);
		
		String fileName = fileInfo.getFileName();
		String saveFileName = FILE_PATH + fileInfo.getSaveFileName(); // 맥일 경우 "/tmp/connect.png" 로 수정
		String contentType = fileInfo.getContentType();
		
		File file = new File(saveFileName);
        
		try (FileInputStream fis = new FileInputStream(file); OutputStream out = response.getOutputStream();) {
			
	        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
	        response.setHeader("Content-Transfer-Encoding", "binary");
	        response.setHeader("Content-Type", contentType);
	        response.setHeader("Content-Length", "" + file.length());
	        response.setHeader("Pragma", "no-cache;");
	        response.setHeader("Expires", "-1;");
	        
			int readCount = 0;
			byte[] buffer = new byte[1024];
			
			while ((readCount = fis.read(buffer)) != -1) {
				out.write(buffer, 0, readCount);
			}
			
		} catch (Exception ex) {
			throw new FileNotFoundException("없는 파일 요청입니다.");
		}
	}
}
