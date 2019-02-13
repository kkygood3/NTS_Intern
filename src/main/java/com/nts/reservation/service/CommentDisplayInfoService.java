package com.nts.reservation.service;

import java.util.List;

import com.nts.reservation.dto.CommentDisplayInfo;

public interface CommentDisplayInfoService {
	public List<CommentDisplayInfo> getCommentDisplayInfos(long productId, int start, int limit);
}
