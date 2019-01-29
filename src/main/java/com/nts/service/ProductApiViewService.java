package com.nts.service;

import java.util.List;

import com.nts.dto.ProductApiView;

public interface ProductApiViewService {
	public static final Integer LIMIT = 4;
	
	public List<ProductApiView> getCategoryProducts(long categoryId, long start);
	
	public List<ProductApiView> getAllProducts(long start);
	
	public int getCount(int categoryId);
}
