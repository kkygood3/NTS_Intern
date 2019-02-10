package com.nts.reservation.service;

import com.nts.reservation.dto.detail.DetailResponse;

public interface DetailResponseService {
	/**
	 * api/products response에 사용.
	 * Comment, CommentImage, DisplayInfo, DisplayImage, ProductImage, ProductImage를 조회하고 DetailResponse를 생성
	 * @param	조회할 DisplayInfo의 ID
	 * @return	DetailResponse Object
	 */
	DetailResponse getDetailDisplay(int displayInfoId);
}
