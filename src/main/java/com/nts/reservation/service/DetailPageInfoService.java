package com.nts.reservation.service;

import com.nts.reservation.dto.ProductPageInfo;

/**
 * 상세페이지 관련 서비스 로직 수행하는 클래스
 * @author si yoon
 *
 */
public interface DetailPageInfoService {
	/**
	 * 상품에 맞는 상세페이지 정보 가져온다
	 * @param displayInfoId 조회할 상품
	 * @return 상세페이지 정보
	 */
	public ProductPageInfo getProductPageInfoByDisplayInfoId(long displayInfoId);
}
