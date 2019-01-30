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

	public static final String SELECT_PROMOTION = "SELECT prom.id AS id"
		+ ", prom.product_id AS productId "
		+ ", fi.file_name AS productImageUrl "
		+ "FROM promotion prom "
		+ "INNER JOIN product_image pi ON prom.product_id = pi.product_id "
		+ "INNER JOIN file_info fi ON fi.id = pi.file_id AND pi.type='th'";

	public static final String SELECT_PRODUCTS_COUNT = "SELECT count(id) FROM display_info";

	public static final String SELECT_ALL_PRODUCTS_PAGING = "SELECT di.id AS displayInfoId "
		+ ",di.product_id AS productId "
		+ ",pdt.description AS productDescription "
		+ ",di.place_name AS placeName "
		+ ",pdt.content AS productContent "
		+ ",fi.file_name AS productImageUrl "
		+ "FROM display_info di "
		+ "INNER JOIN product pdt ON pdt.id = di.product_id "
		+ "INNER JOIN product_image pi ON pi.product_id = di.product_id AND pi.type = 'th' "
		+ "INNER JOIN file_info fi ON fi.id = pi.file_id "
		+ "ORDER BY di.product_id ASC LIMIT :start, :limit";

	public static final String SELECT_ALL_PRODUCTS_BY_CATEGORY_PAGING = "SELECT di.id AS displayInfoId "
		+ ",di.product_id AS productId "
		+ ",pdt.description AS productDescription "
		+ ",di.place_name AS placeName "
		+ ",pdt.content AS productContent "
		+ ",fi.file_name AS productImageUrl "
		+ "FROM product pdt "
		+ "INNER JOIN display_info di ON pdt.id = di.product_id "
		+ "INNER JOIN product_image pi ON di.product_id = pi.product_id "
		+ "INNER JOIN file_info fi ON fi.id = pi.file_id AND pi.type = 'th' "
		+ "WHERE pdt.category_id =:category_id "
		+ "ORDER BY di.product_id ASC LIMIT :start, :limit";

	public static final String SELECT_PRODUCTS_COUNT_BY_CATEGORY = "SELECT ctg.id AS id, "
		+ "ctg.name AS NAME, "
		+ "count(*) as COUNT "
		+ "FROM display_info di "
		+ "INNER JOIN product pdt ON pdt.id = di.product_id "
		+ "INNER JOIN category ctg ON ctg.id = pdt.category_id "
		+ "GROUP BY pdt.category_id;";
}
