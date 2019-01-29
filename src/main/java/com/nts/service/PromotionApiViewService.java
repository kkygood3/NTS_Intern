package com.nts.service;

import java.util.List;

import com.nts.dto.PromotionApiView;

public interface PromotionApiViewService {
	public List<PromotionApiView> getAllPromotions();

	public int getCount();
}
