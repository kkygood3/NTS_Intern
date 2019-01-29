package com.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nts.reservation.dao.CategoryDao;
import com.nts.reservation.dto.Category;
import com.nts.reservation.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDao promotionDao;
	@Override
	public List<Category> getCategoris() {
		List<Category> list = promotionDao.selectAll();
		return list;
	}

}
