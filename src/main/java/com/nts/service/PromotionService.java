package com.nts.service;

import java.util.List;

import com.nts.dto.Promotion;

public interface PromotionService {
	public List<Promotion> getAllPromotions();
	public int getCount();
}
