/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.nts.reservation.dto.Comment;

/**
* @author  : 이승수
*/
public interface CommentService {
	public void addComment(Comment comment, List<MultipartFile> files) throws IOException;
}
