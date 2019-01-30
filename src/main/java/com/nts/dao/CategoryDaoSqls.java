/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.dao;

/**
 * 카테고리 테이블에 대한 SQL문을 모아둔 클래스
 * @author jinwoo.bae
 */
public class CategoryDaoSqls {
	public static final String SELECT_ALL_WITH_PRODUCT_COUNT = 
		"SELECT  " + 
		"    category.id, category.name, COUNT(*) AS count " + 
		"FROM " + 
		"    category, " + 
		"    product, " + 
		"    display_info " + 
		"WHERE " + 
		"    category.id = product.category_id " + 
		"        AND product.id = display_info.product_id " + 
		"GROUP BY product.category_id";
}