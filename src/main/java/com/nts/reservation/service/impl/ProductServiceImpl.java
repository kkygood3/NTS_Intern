package com.nts.reservation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.ProductDao;
import com.nts.reservation.service.ProductService;

/**
 * 상품관련 서비스 로직 수행하는 클래스
 * @author si yoon
 *
 */
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;

	/**
	 * 0일경우 전체 그외 해당 카테고리의 상품수 리턴
	 */
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
