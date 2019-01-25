package com.nts.reservation.service;

import java.util.List;

import com.nts.reservation.dto.Product;

public interface ProductService {
	public static final Integer LIMIT = 4;

	public List<Product> getProducts(Integer start);

	public int getCount();

	public List<Product> getPromotions();

	public int getPromotionCount();

}
