/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.dao;


/**
 *
 * @description : CategoryDaoSqls
 * @package : com.nts.dao
 * @filename : CategoryDaoSqls.java
 * @author : 최석현
 * 
 */
public class CategoryDaoSqls {
	
	public static final String SELECT_CATEGORY_GROUP = "SELECT count(*), category.id, category.name "
														+ "FROM category, product, display_info "
														+ "WHERE category.id = product.category_id "
															+ "and product.id = display_info.product_id "
														+ "GROUP BY category.id";
	
	public static final String SELECT_COUNTS = "SELECT count(*) "
											 + "FROM category, product, display_info "
											 + "WHERE category.id = product.category_id "
													+ "and product.id = display_info.product_id "
											 + "GROUP BY category.id "
											 + "ORDER BY category.id";
}
