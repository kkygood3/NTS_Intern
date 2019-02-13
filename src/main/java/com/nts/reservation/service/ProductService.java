package com.nts.reservation.service;

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
	public int getCount(int categoryId);
}
