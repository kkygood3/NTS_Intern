package com.nts.reservation.service;

import java.util.List;

import com.nts.reservation.dto.Product;

public interface ProductService {
	public static final Integer LIMIT = 4;
	public List<Product> getProducts(int start);
	public List<Product> getProductsByCategory(int start, int categoryId);
//	public int deleteGuestBook(Long id, String ip);
//	public Product addGuestBook(Product guestBook, String ip);
	public int getCount();
	public int getCount(int categoryId);
}
