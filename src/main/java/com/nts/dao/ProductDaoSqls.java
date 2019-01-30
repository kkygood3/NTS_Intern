/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.dao;


/**
 *
 * @description : ProductDaoSqls
 * @package : com.nts.dao
 * @filename : ProductDaoSqls.java
 * @author : 최석현
 * 
 */
public class ProductDaoSqls {
	public static final String SELECT_COUNT = "SELECT count(*) "
											+ "FROM display_info, product "
											+ "WHERE display_info.product_id = product.id ";
	
	public static final String SELECT_PAGING_PREFIX = "SELECT display_info.id, place_name, "
														+ "content as productContent, "
														+ "description as productDescription, "
														+ "display_info.product_id, "
														+ "save_file_name as product_image_url "
													+ "FROM product, display_info, product_image, file_info "
													+ "WHERE product_image.type = 'th' "
														+ "and product.id = display_info.product_id "
														+ "and product.id = product_image.product_id "
														+ "and file_info.id = product_image.file_id ";

	public static final String APPEND_CATEGORY_ID =		  "and product.category_id = ";
	
	public static final String SELECT_PAGING_SUFFIX = " ORDER BY product.id asc limit :start, 4";
	
}