package com.nts.reservation.service;

import java.util.List;

import com.nts.reservation.dto.Category;

/**
 * 카테고리 관련 서비스 로직 수행하는 인터페이스
 * @author si yoon
 *
 */
public interface CategoryService {
	/**
	 * 전체 카테고리 셀렉트
	 * @return 전체 카테고리 리스트
	 */
	List<Category> getCategoris();
}
