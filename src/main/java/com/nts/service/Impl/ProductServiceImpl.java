package com.nts.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.dao.ProductDao;
import com.nts.dto.Product;
import com.nts.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;
	
	@Override
	public List<Product> getProducts(Integer start) {
		return productDao.selectPaging(start, LIMIT);
	}

	@Override
	public Product getProductById(Product product) {
		return productDao.selectById(product.getId());
	}

	@Override
	public int getCount() {
		return productDao.selectCount();
	}

}
