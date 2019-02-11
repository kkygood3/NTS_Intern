package com.nts.service;

import java.util.List;

import com.nts.dto.commentdto.Comment;
import com.nts.dto.commentdto.CommentImage;

public interface CommentService {

	public double getAverageScoreByDisplayInfoId(int displayInfoId);

	public List<Comment> getCommentsByDisplayInfoId(int displayInfoId);
	
	public List<CommentImage> getCommentImagesByDisplayInfoId(int displayInfoId);
}
