package com.nts.reservation.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.Category;

/**
 * 카테고리 테이블 접근하는 클래스
 * 
 * @author si yoon
 *
 */
@Repository
public interface CategoryMapper {
	/**
	 * 모든 카테고리 셀렉트하는 메소드
	 * 
	 * @return 전체 카테고리 목록
	 */
	public List<Category> selectAllCategoris();
}
