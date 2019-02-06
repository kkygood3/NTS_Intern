/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.project3.service;

import java.util.List;

import com.nts.project3.dto.PromotionDto;

/**
 * 프로모션에 대한 서비스 인터페이스
 * @author jinwoo.bae
 */
public interface PromotionService {
	int SELECT_LIMIT = 20;
	List<PromotionDto> getPromotions();
}
