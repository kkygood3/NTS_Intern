/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto.detail;

import java.util.List;

/**
 * detail 페이지 로드 시 /api/products/{displayInfoId} 요청에  detail 페이지의 정보를 담는 DTO
 */
public class DetailResponse {
	private DetailDisplayInfo detailDisplayInfo;
	private List<DetailComment> detailComment;

	public DetailDisplayInfo getDetailDisplayInfo() {
		return detailDisplayInfo;
	}

	public void setDetailDisplayInfo(DetailDisplayInfo detailDisplayInfo) {
		this.detailDisplayInfo = detailDisplayInfo;
	}

	public List<DetailComment> getDetailComment() {
		return detailComment;
	}

	public void setDetailComment(List<DetailComment> detailComment) {
		this.detailComment = detailComment;
	}
}
