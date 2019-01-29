package com.nts.reservation.service;

import java.util.Map;

public interface ProductService {
	public static final Integer LIMIT = 4;

	public Map<String, Object> getProductsByCategory(Integer start, Integer category);

	public Map<String, Object> getPromotions();

	public Map<String, Object> getProductsCountByCategory();
}
