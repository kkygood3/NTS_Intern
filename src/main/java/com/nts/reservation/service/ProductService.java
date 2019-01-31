package com.nts.reservation.service;

public interface ProductService {
	public static final Integer LIMIT = 4;
	public int getCount();
	public int getCount(int categoryId);
}
