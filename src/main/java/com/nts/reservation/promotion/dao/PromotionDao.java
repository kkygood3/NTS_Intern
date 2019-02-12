/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.promotion.dao;

import java.util.List;

import com.nts.reservation.promotion.dto.Promotion;

public interface PromotionDao {
	List<Promotion> selectPagingCategories();
}
