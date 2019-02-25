/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.service.comment;

import java.io.FileNotFoundException;
import java.util.List;

import com.nts.dto.comment.Comment;
import com.nts.dto.comment.Comments;
import com.nts.dto.file.FileInfo;

/**
 * @author 전연빈
 */
public interface CommentService {
	List<Comment> getCommentListByProductId(int productId);

	double getAverageScoreByProductId(int productId);

	Comments getComments(int productId);
	
	FileInfo getFileInfoByCommentId(int commentId) throws FileNotFoundException;
}
