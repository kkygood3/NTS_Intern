package com.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.PromotionMapper;
import com.nts.reservation.service.PromotionService;

/**
 * 프로모션 관련 서비스 로직 수행하는 클래스
 * 
 * @author si yoon
 *
 */
@Service
public class PromotionServiceImpl implements PromotionService {
	@Autowired
	private PromotionMapper promotionDao;

	@Override
	public List<String> getPromotions() {
		return promotionDao.selectThTypeFileNames();
	}
}
