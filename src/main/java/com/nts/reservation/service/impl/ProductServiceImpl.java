package com.nts.reservation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.ProductDao;
import com.nts.reservation.service.ProductService;

/*
 * 전체 상품 수 리턴
 * @author 시윤
 */
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDao productDao;

	@Override
	@Transactional
	public int getCount(int categoryId) {
		if (categoryId == 0) {
			return productDao.selectCount();
		} else {
			return productDao.selectCount(categoryId);
		}
	}
}
