package com.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.PromotionDao;
import com.nts.reservation.dto.Promotion;
import com.nts.reservation.service.PromotionService;

/*
 * 프로모션 이미지 이름 리턴
 * @author 시윤
 */
@Service
public class PromotionServiceImpl implements PromotionService {
	@Autowired
	PromotionDao promotionDao;

	@Override
	@Transactional
	public List<Promotion> getPromotions() {
		List<Promotion> list = promotionDao.selectAll();
		return list;
	}
}
