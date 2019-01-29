package com.nts.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.dao.ProductApiViewDao;
import com.nts.dto.ProductApiView;
import com.nts.service.ProductApiViewService;

@Service
public class ProductApiViewServiceImpl implements ProductApiViewService {

	@Autowired
	ProductApiViewDao productApiViewDao;

	@Override
	public List<ProductApiView> getCategoryProducts(long categoryId, long start) {
		if(categoryId > 0)
			return productApiViewDao.selectPagingProducts(categoryId, start, LIMIT);
		else
			return getAllProducts(start);
	}

	@Override
	public List<ProductApiView> getAllProducts(long start) {
		return productApiViewDao.selectAllPagingProducts(start, LIMIT);
	}

	@Override
	public int getCount(int categoryId) {
		return productApiViewDao.selectCount(categoryId);
	}
}
