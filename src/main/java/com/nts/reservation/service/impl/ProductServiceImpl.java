package com.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.ProductDao;
import com.nts.reservation.dto.Category;
import com.nts.reservation.dto.Product;
import com.nts.reservation.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDao productDao;

	@Override
	@Transactional // 내부적으로 readonly로 처리
	public List<Product> getProducts(int start) {
		List<Product> list = productDao.selectAll(start, LIMIT);
		return list;
	}

	@Override
	public List<Product> getProductsByCategory(int start, int categoryId) {
		Category c = new Category();
		c.setId(categoryId);
		List<Product> list = productDao.selectByCategory(c, start, LIMIT);
		return list;
	}

	@Override
	@Transactional
	public int getCount() {
		return productDao.selectCount();
	}
}
