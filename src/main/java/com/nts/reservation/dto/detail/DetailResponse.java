/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto.detail;

import java.util.List;

import com.nts.reservation.dto.common.Comment;

/**
 * detail 페이지 로드 시 /api/products/{displayInfoId} 요청에  detail 페이지의 정보 DTO
 */
public class DetailResponse {
	private DetailDisplayInfo detailDisplayInfo;
	private List<Comment> detailComment;

	public DetailDisplayInfo getDetailDisplayInfo() {
		return detailDisplayInfo;
	}

	public void setDetailDisplayInfo(DetailDisplayInfo detailDisplayInfo) {
		this.detailDisplayInfo = detailDisplayInfo;
	}

	public List<Comment> getDetailComment() {
		return detailComment;
	}

	public void setDetailComment(List<Comment> detailComment) {
		this.detailComment = detailComment;
	}
}
