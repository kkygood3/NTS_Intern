package com.nts.reservation.service;

import java.util.List;

import com.nts.reservation.dto.Promotion;

/**
 * 프로모션 관련 서비스 로직 수행하는 클래스
 * @author si yoon
 *
 */
public interface PromotionService {
	/**
	 * 프로모션 이미지 전부 가져온다
	 * @return 프로모션 이미지 리스트
	 */
	List<Promotion> getPromotions();
}
