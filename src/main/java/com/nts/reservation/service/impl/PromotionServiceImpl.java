/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.constant.ImageType;
import com.nts.reservation.dto.PromotionDto;
import com.nts.reservation.mapper.PromotionMapper;
import com.nts.reservation.service.PromotionService;

/**
 * 프로모션에 대한 서비스 로직
 * @author jinwoo.bae
 */
@Service
@Transactional(readOnly = true)
public class PromotionServiceImpl implements PromotionService {
	@Autowired
	private PromotionMapper promotionMapper;

	/**
	 * 먼저 등록된 순으로 SELECT_LIMIT값 수만큼 프로모션을 가져옵니다.
	 */
	@Override
	public List<PromotionDto> getPromotions(ImageType type, int limit) {
		return promotionMapper.selectPromotions(type, limit);
	}

}
