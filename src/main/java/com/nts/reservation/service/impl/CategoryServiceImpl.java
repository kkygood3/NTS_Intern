package com.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.ProductDao;
import com.nts.reservation.dto.product.Category;
import com.nts.reservation.service.CategoryService;

@Service
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	ProductDao productDao;

	@Override
	public List<Category> getAllProductsCountByCategory() {
		return productDao.selectAllProductsCountByCategory();
	}
}
