package com.nts.reservation.service;

import java.util.List;

import com.nts.reservation.dto.ProductThumbnail;

/**
 * 메인페이지 썸네일 관련 서비스 로직 수행하는 클래스
 * @author si yoon
 *
 */
public interface ProductThumbnailService {
	/**
	 * 썸네일 정보를 가져온다
	 * @param categoryId 조회할 카테고리. 0이면 전체카테고리 
	 * @param start 시작 인덱스
	 * @param limit 페이징 사이즈
	 * @return 썸네일정보 리스트
	 */
	public List<ProductThumbnail> getProductThumbnailsByCategoryIdWithPaging(int categoryId, int start, int limit);
}
