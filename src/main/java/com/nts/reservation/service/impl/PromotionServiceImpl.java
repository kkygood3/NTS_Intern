package com.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.PromotionMapper;
import com.nts.reservation.service.PromotionService;

/**
 * 프로모션 관련 서비스 로직 수행하는 클래스
 * @author si yoon
 *
 */
@Service
public class PromotionServiceImpl implements PromotionService {
	@Autowired
	private PromotionMapper promotionDao;

	/**
	 * 전체 프로모션 이미지 리스트 리턴
	 */
	@Override
	@Transactional
	public List<String> getPromotions() {
		List<String> list = promotionDao.selectThTypeFileNames();
		return list;
	}
}
