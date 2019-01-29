package com.nts.reservation.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.ProductDao;
import com.nts.reservation.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;

	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> getProductsByCategory(Integer start, Integer category) {
		Map<String, Object> result = new HashMap<>();
		if (category > 0 && category <= 5) {
			result.put("items", productDao.getProductsByCategory(start, category, LIMIT));
		} else {
			result.put("items", productDao.getProducts(start, LIMIT));
		}
		result.put("totalCount", productDao.getProductsCount());
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> getPromotions() {
		Map<String, Object> result = new HashMap<>();
		result.put("items", productDao.getPromotions());
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> getProductsCountByCategory() {
		Map<String, Object> result = new HashMap<>();
		result.put("items", productDao.getProductsCountByCategory());
		return result;
	}

}
