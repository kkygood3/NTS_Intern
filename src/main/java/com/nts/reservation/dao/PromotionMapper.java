package com.nts.reservation.dao;

import java.util.List;

/**
 * 프로모션관련 DB접근 클래스
 * 
 * @author si yoon
 *
 */
public interface PromotionMapper {
	/**
	 * promotion 과 join해서 썸네일 이미지를 가져온다.
	 * 
	 * @return 썸네일 이미지 리스트
	 */
	List<String> selectThTypeFileNames();
}
