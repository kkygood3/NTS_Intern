package com.nts.reservation.category.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.category.dao.CategoryDao;
import com.nts.reservation.category.dto.CategoryResponse;
import com.nts.reservation.category.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	@Override
	public CategoryResponse getCategories() {
		return CategoryResponse.builder()
			.items(categoryDao.selectCategories()).build();
	}
}
