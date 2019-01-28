package com.nts.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.dao.PromotionDao;
import com.nts.dto.Promotion;
import com.nts.service.PromotionService;

@Service
public class PromotionServiceImpl implements PromotionService{

	@Autowired
	PromotionDao promotionDao;
	
	@Override
	public List<Promotion> getAllPromotions() {
		return promotionDao.selectAll();
	}
	
	@Override
	public int getCount() {
		return promotionDao.selectCount();
	}
}
