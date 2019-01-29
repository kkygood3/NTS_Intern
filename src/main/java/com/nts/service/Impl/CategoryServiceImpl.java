package com.nts.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.dao.CategoryDao;
import com.nts.dto.Category;
import com.nts.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDao categoryDao;

	@Override
	public List<Category> getAllCategories() {
		return categoryDao.selectAllCategories();
	}
}
