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
	public static final String SELECT_ALL_WITH_PRODUCT_COUNT = "SELECT c.id, c.name, count(p.category_id) as count FROM category c, product p WHERE c.id = p.category_id GROUP BY p.category_id";
}