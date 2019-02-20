/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.sql;

/**
 * 상품 데이터에 대한 SQL문을 모아둔 클래스
 * @author jinwoo.bae
 */
public class ProductDaoSqls {
	public static final String SELECT_PRODUCTS = ""
		+ "SELECT  "
		+ "    di.id AS displayInfoId, "
		+ "    p.id AS productId, "
		+ "    p.description AS productDescription, "
		+ "    di.place_name AS placeName, "
		+ "    p.content AS productContent, "
		+ "    fi.save_file_name AS productImageUrl "
		+ "FROM "
		+ "    product p, "
		+ "    product_image pi, "
		+ "    display_info di, "
		+ "    display_info_image dii, "
		+ "    file_info fi "
		+ "WHERE "
		+ "    pi.type = 'th' "
		+ "        AND p.id = pi.product_id "
		+ "        AND p.id = di.product_id "
		+ "        AND di.id = dii.display_info_id "
		+ "        AND fi.id = pi.file_id "
		+ "ORDER BY di.id ASC LIMIT :start, :limit";
	public static final String SELECT_PRODUCTS_BY_CATEGORY_ID = ""
		+ "SELECT  "
		+ "    di.id AS displayInfoId, "
		+ "    p.id AS productId, "
		+ "    p.description AS productDescription, "
		+ "    di.place_name AS placeName, "
		+ "    p.content AS productContent, "
		+ "    fi.save_file_name AS productImageUrl "
		+ "FROM "
		+ "    product p, "
		+ "    product_image pi, "
		+ "    display_info di, "
		+ "    display_info_image dii, "
		+ "    file_info fi "
		+ "WHERE "
		+ "    p.category_id = :categoryId AND pi.type = 'th' "
		+ "        AND p.id = pi.product_id "
		+ "        AND p.id = di.product_id "
		+ "        AND di.id = dii.display_info_id "
		+ "        AND fi.id = pi.file_id "
		+ "ORDER BY di.id ASC LIMIT :start, :limit";
	public static final String SELECT_COUNT = ""
		+ "SELECT "
		+ "    COUNT(p.id) AS count "
		+ "FROM "
		+ "    product p, "
		+ "    display_info di "
		+ "WHERE "
		+ "    p.id = di.product_id";
	public static final String SELECT_COUNT_BY_CATEGORY_ID = ""
		+ "SELECT "
		+ "    COUNT(p.id) AS count "
		+ "FROM "
		+ "    product p, "
		+ "    display_info di "
		+ "WHERE "
		+ "    p.id = di.product_id"
		+ "        AND p.category_id = :categoryId";
	public static final String SELECT_PRODUCT = ""
		+ "SELECT "
		+ "    id AS productId, "
		+ "    content AS productContent, "
		+ "    description AS productDescription "
		+ "FROM "
		+ "    product "
		+ "WHERE "
		+ "    id = :productId ";
	public static final String SELECT_PRODUCT_PRICES = ""
		+ "SELECT "
		+ "    id, "
		+ "    price_type_name AS type, "
		+ "    price, "
		+ "    discount_rate "
		+ "FROM "
		+ "    product_price "
		+ "WHERE "
		+ "    product_id = :productId "
		+ "ORDER BY id ";
}