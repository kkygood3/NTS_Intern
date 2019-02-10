package com.nts.reservation.service;

import com.nts.reservation.dto.Product;

public interface ProductService {
	public int getCount(int categoryId);
	public Product getProduct(long productId);
}
