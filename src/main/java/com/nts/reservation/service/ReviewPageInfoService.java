package com.nts.reservation.service;

import com.nts.reservation.dto.ReviewPageInfo;

/**
 * 리뷰페이지 관련 서비스 로직 수행하는 클래스
 * @author si yoon
 *
 */
public interface ReviewPageInfoService {
	/**
	 * 상품에 맞는 리뷰페이지 정보를 가져온다
	 * @param displayInfoId 조회할 상품
	 * @return 리뷰페이지 정보
	 */
	public ReviewPageInfo getReviewPageInfo(long displayInfoId);
}
