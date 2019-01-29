package com.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.ProductDao;
import com.nts.reservation.dto.Category;
import com.nts.reservation.dto.Product;
import com.nts.reservation.dto.Promotion;
import com.nts.reservation.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;

	@Override
	public List<Product> getProducts(Integer start, Integer category) {
		if (category == 0) {
			return productDao.getProducts(start, LIMIT);
		} else {
			return productDao.getProductsByCategory(start, category, LIMIT);
		}

	}

	@Override
	@Transactional
	public List<Promotion> getPromotions() {
		return productDao.getPromotions();
	}

	@Override
	public Long getProductsCount() {
		return productDao.getProductsCount();
	}

	@Override
	public List<Category> getProductsCountByCategory() {
		return productDao.getProductsCountByCategory();
	}

}
