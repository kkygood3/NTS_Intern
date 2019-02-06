/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.project3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.project3.dao.PromotionDao;
import com.nts.project3.dto.PromotionDto;

/**
 * 프로모션에 대한 서비스 로직
 * @author jinwoo.bae
 */
@Service
@Transactional(readOnly = true)
public class PromotionServiceImpl implements PromotionService {
	@Autowired
	private PromotionDao promotionDao;

	/**
	 * 먼저 등록된 순으로 SELECT_LIMIT값 수만큼 프로모션을 가져옵니다.
	 */
	@Override
	public List<PromotionDto> getPromotions() {
		return promotionDao.selectLimitedList(SELECT_LIMIT);
	}

}
