/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.sqls;

/**
* @author  : 이승수
*/
public class ProductSqls {
	private static final String PRODUCT_TABLE = "product as p "
		+ "INNER JOIN display_info as d "
		+ "ON p.id = d.product_id "
		+ "INNER JOIN product_image as i "
		+ "ON p.id = i.product_id "
		+ "INNER JOIN file_info as f "
		+ "ON i.file_id = f.id ";
	private static final String PRODUCT_COULMN = "d.id AS display_info_id"
		+ ", d.place_name "
		+ ", p.content AS product_content"
		+ ", p.description AS product_description"
		+ ", p.id AS product_id"
		+ ", f.save_file_name AS product_image_url ";

	public static final String LIMIT = "LIMIT :start, :limit ";

	public static final String SELECT_COUNT_ALL = "SELECT count(*) FROM " + PRODUCT_TABLE + "WHERE i.type = 'th'";
	public static final String SELECT_COUNT_BY_CATEGORY_ID = SELECT_COUNT_ALL + "AND p.category_id = :categoryId ";

	public static final String SELECT_PRODUCTS = "SELECT " + PRODUCT_COULMN + "FROM " + PRODUCT_TABLE
		+ "WHERE i.type = 'th'";
	public static final String SELECT_PRODUCTS_BY_CATEGORY_ID = SELECT_PRODUCTS + "AND p.category_id = :categoryId ";

	public static final String SELECT_PROMOTION_PRODUCTS = "SELECT " + PRODUCT_COULMN + "FROM " + PRODUCT_TABLE
		+ "INNER JOIN promotion as pr "
		+ "ON pr.product_id = p.id "
		+ "WHERE i.type = 'th'";
	public static final String SELECT_COUNT_PROMOTION = "SELECT count(*) FROM " + PRODUCT_TABLE
		+ "INNER JOIN promotion as pr "
		+ "ON pr.product_id = p.id "
		+ "WHERE i.type = 'th'";

	public static final String SELECT_CATEGORIES = "SELECT c.id, c.name, count(*) AS count "
		+ "FROM category as c "
		+ "INNER JOIN product as p "
		+ "ON c.id = p.category_id "
		+ "INNER JOIN display_info as d "
		+ "ON p.id = d.product_id "
		+ "GROUP BY c.id ";
}