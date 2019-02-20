/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.sql;

public class MainSqls {
	public static final String SELECT_PRODUCT_COUNT_BY_CATEGORY = 
		"SELECT" + 
		" COUNT(d.id)" + 
		" FROM product AS p" + 
		" INNER JOIN display_info AS d ON p.id = d.product_id" + 
		" WHERE p.category_id = :categoryId";

	public static final String SELECT_PRODUCT_COUNT = 
		"SELECT" + 
		" COUNT(d.id)" + 
		" FROM product AS p" + 
		" INNER JOIN display_info AS d ON p.id = d.product_id";

	public static final String SELECT_PRODUCT_PAGE_BY_CATEGORY = 
		"SELECT" + 
		" d.id AS display_info_id," + 
		" d.place_name," + 
		" p.content AS product_content," + 
		" p.description AS product_description," + 
		" p.id AS product_id," + 
		" f.save_file_name AS product_image_url" + 
		" FROM product AS p" + 
		" INNER JOIN display_info AS d ON p.id = d.product_id" + 
		" INNER JOIN product_image AS pi ON p.id = pi.product_id AND pi.type='th'" + 
		" INNER JOIN file_info AS f ON pi.file_id = f.id" + 
		" WHERE p.category_id = :categoryId" + 
		" LIMIT :start, :pagingLimit";

	public static final String SELECT_PRODUCT_PAGE = 
		"SELECT" + 
		" d.id AS display_info_id," + 
		" d.place_name," + 
		" p.content AS product_content," + 
		" p.description AS product_description," + 
		" p.id AS product_id," + 
		" f.save_file_name AS product_image_url" + 
		" FROM product AS p" + 
		" INNER JOIN display_info AS d ON p.id = d.product_id" + 
		" INNER JOIN product_image AS pi ON p.id = pi.product_id AND pi.type='th'" + 
		" INNER JOIN file_info AS f ON pi.file_id = f.id" + 
		" LIMIT :start, :pagingLimit";
	
	public static final String SELECT_CATEGORIES = 
		"SELECT" + 
		" c.id," + 
		" c.name," + 
		" COUNT(d.id) AS count" + 
		" FROM category AS c" + 
		" INNER JOIN product AS p ON c.id = p.category_id" + 
		" INNER JOIN display_info AS d ON p.id = d.product_id" + 
		" GROUP BY c.id" + 
		" LIMIT :pagingLimit";

	public static final String SELECT_PROMOTION_COUNT = 
		"SELECT" + 
		" COUNT(d.id)" + 
		" FROM promotion AS pm" + 
		" INNER JOIN display_info AS d ON pm.product_id = d.product_id";

	public static final String SELECT_PROMOTION = 
		"SELECT" + 
		" d.id AS display_info_id," + 
		" f.save_file_name AS product_image_url" + 
		" FROM promotion AS pm" + 
		" INNER JOIN display_info AS d ON pm.product_id = d.product_id" + 
		" INNER JOIN product_image AS pi ON pm.product_id = pi.product_id AND pi.type = 'th'" + 
		" INNER JOIN file_info AS f ON pi.file_id = f.id" + 
		" GROUP BY pm.product_id" + 
		" LIMIT :pagingLimit";
}
