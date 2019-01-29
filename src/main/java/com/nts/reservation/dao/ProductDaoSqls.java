package com.nts.reservation.dao;

/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 */

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

public class ProductDaoSqls {

	public static final String SELECT_PROMOTION = "SELECT prom.id AS id, "
		+ "prom.product_id AS productId, "
		+ "CONCAT('img/',pi.product_id,'_',pi.type,'_',pi.id,'.png') AS productImageUrl "
		+ "FROM promotion prom "
		+ "INNER JOIN "
		+ "product_image pi "
		+ "ON prom.product_id = pi.product_id "
		+ "WHERE pi.type='th'";

	public static final String SELECT_PRODUCTS_COUNT = "SELECT count(*) FROM display_info";

	public static final String SELECT_ALL_PRODUCTS = "SELECT di.id AS displayInfoId, "
		+ "di.product_id AS productId, "
		+ "p1.description AS productDescription, "
		+ "di.place_name AS placeName, "
		+ "p1.content AS productContent, "
		+ "CONCAT('img/',pi.product_id,'_',pi.type,'_',pi.id,'.png') as productImageUrl "
		+ "FROM product p1 "
		+ "INNER JOIN display_info di "
		+ "INNER JOIN product_image pi "
		+ "ON p1.id = di.product_id AND di.product_id = pi.product_id "
		+ "WHERE pi.type = 'th' "
		+ "ORDER BY di.product_id ASC limit :start, :limit";

	public static final String SELECT_ALL_PRODUCTS_BY_CATEGORY = "SELECT di.id AS displayInfoId, "
		+ "di.product_id AS productId, "
		+ "p1.description AS productDescription, "
		+ "di.place_name AS placeName, "
		+ "p1.content AS productContent, "
		+ "CONCAT('img/',pi.product_id,'_',pi.type,'_',pi.id,'.png') as productImageUrl "
		+ "FROM product p1 "
		+ "INNER JOIN display_info di "
		+ "INNER JOIN product_image pi "
		+ "ON p1.id = di.product_id "
		+ "AND di.product_id = pi.product_id "
		+ "WHERE p1.category_id =:category_id "
		+ "AND pi.type = 'th' ORDER BY di.product_id ASC limit :start, :limit";

	public static final String SELECT_PRODUCTS_COUNT_BY_CATEGORY = "SELECT ctg.id AS id, "
		+ "ctg.name AS NAME, "
		+ "count(*) as COUNT "
		+ "FROM display_info di "
		+ "INNER JOIN product p1 "
		+ "INNER JOIN category ctg "
		+ "ON p1.id = di.product_id AND ctg.id = p1.category_id "
		+ "GROUP BY p1.category_id;";
}
