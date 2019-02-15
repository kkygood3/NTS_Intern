/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.impl.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.main.HomeCategoryDao;
import com.nts.reservation.dto.main.HomeCategory;
import com.nts.reservation.service.main.HomeCategoryService;

@Service
public class HomeCategoryServiceImpl implements HomeCategoryService {

	@Autowired
	private HomeCategoryDao homeCategoryDao;

	@Override
	public List<HomeCategory> getCategories(int pagingLimit) {
		return homeCategoryDao.selectCategories(pagingLimit);
	}

}
