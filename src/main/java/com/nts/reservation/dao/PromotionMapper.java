package com.nts.reservation.dao;

import java.util.List;

public interface PromotionMapper {
	/**
	 * promotion 과 join해서 썸네일 이미지를 가져온다.
	 * @return 썸네일 이미지 리스트
	 */
	List<String> selectThTypeFileNames();
}
