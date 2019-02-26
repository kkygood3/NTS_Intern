/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.service.file.impl;

import java.io.FileNotFoundException;

import org.springframework.stereotype.Service;

import com.nts.dao.comment.CommentRepository;
import com.nts.dto.file.FileInfo;
import com.nts.service.file.FileService;

/**
 * @author 전연빈
 */
@Service
public class FileServiceImpl implements FileService{

	private final CommentRepository commentRepository;
	
	public FileServiceImpl(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}
	
	@Override
	public FileInfo getFileInfoByCommentId(int commentId) throws FileNotFoundException {
		FileInfo fileInfo = commentRepository.selectFileInfoByCommentId(commentId);
		
		fileInfo.setSaveFileName(FILE_PATH + fileInfo.getSaveFileName());
		return fileInfo;
	}

}
