package com.nts.reservation.service;

import java.util.List;

import com.nts.reservation.dto.Category;
import com.nts.reservation.dto.Product;
import com.nts.reservation.dto.Promotion;

public interface ProductService {
	public static final Integer LIMIT = 4;

	public List<Product> getProducts(Integer start, Integer category);

	public Long getProductsCount();

	public List<Promotion> getPromotions();

	public List<Category> getProductsCountByCategory();
}
