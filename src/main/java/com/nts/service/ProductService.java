package com.nts.service;

import java.util.List;

import com.nts.dto.Product;

public interface ProductService {
	public static final Integer LIMIT = 4;

	public List<Product> getProducts(Integer start);

	public Product getProductById(Product product);

	public int getCount();
}
