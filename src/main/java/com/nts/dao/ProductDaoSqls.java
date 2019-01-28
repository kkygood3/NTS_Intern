/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.dao;

/**
 * 상품 테이블에 대한 SQL문을 모아둔 클래스
 * @author jinwoo.bae
 */
public class ProductDaoSqls {
	public static final String SELECT_BY_CATEGORY_ID = "SELECT id, category_id, description, content, event, create_date, modify_date FROM product WHERE category_id = :categoryId ORDER BY id ASC limit :start, :limit";
	public static final String SELECT_COUNT_BY_CATEGORY_ID = "SELECT count(*) FROM product WHERE category_id = :categoryId";
}