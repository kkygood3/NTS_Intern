package com.nts.reservation.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.CategoryDao;
import com.nts.reservation.dto.CategoryResponse;
import com.nts.reservation.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	CategoryDao categoryDao;
	
	@Override
	public CategoryResponse getCategories() {
		return CategoryResponse.builder()
			.items(categoryDao.getCategory()).build();
	}

}
