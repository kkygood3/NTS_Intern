package com.nts.reservation.service;

import java.util.List;

import com.nts.reservation.dto.PriceInfo;
import com.nts.reservation.dto.ProductPageInfo;
import com.nts.reservation.dto.ProductThumbnail;

/**
 * 상품 관련 서비스 로직 수행하는 클래스
 * @author si yoon
 *
 */
public interface ProductService {
	/**
	 * 상품수를 가져온다
	 * @param categoryId 조회할 카테고리. 0이면 모든카테고리에서 가져온다.
	 * @return 상품 갯수
	 */
	public int getProductCountByCategoryId(int categoryId);
	
	/**
	 * 썸네일 정보를 가져온다
	 * @param categoryId 조회할 카테고리. 0이면 전체카테고리 
	 * @param start 시작 인덱스
	 * @param limit 페이징 사이즈
	 * @return 썸네일정보 리스트
	 */
	public List<ProductThumbnail> getProductThumbnailsByCategoryIdWithPaging(int categoryId, int start, int limit);
	
	/**
	 * 상품에 맞는 상세페이지 정보 가져온다
	 * @param displayInfoId 조회할 상품
	 * @return 상세페이지 정보
	 */
	public ProductPageInfo getProductPageInfoByDisplayInfoId(long displayInfoId);

	/**
	 * 상품에 맞는 가격정보를 가져온다
	 * @param productId 조회할 상품
	 * @return 상세페이지 정보
	 */
	public List<PriceInfo> getPriceInfoByProductId(long productId);
}
