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
	public static final String SELECT_COUNT = "SELECT "
												+ "count(*)"
											+ "FROM display_info"
												+ ", product "
											+ "WHERE display_info.product_id = product.id ";
	
	public static final String SELECT_PRODUCTS = "SELECT "
														+ " display_info.id AS display_info_id "
														+ ", place_name AS place_name "
														+ ", content AS productContent "
														+ ", description AS productDescription "
														+ ", display_info.product_id AS product_id "
														+ ", save_file_name AS product_image_url "
													+ "FROM "
														+ " product "
														+ ", display_info "
														+ ", product_image "
														+ ", file_info "
													+ "WHERE product_image.type = 'th' "
														+ " and product.id = display_info.product_id "
														+ " and product.id = product_image.product_id "
														+ " and file_info.id = product_image.file_id ";

	public static final String BY_ID =		  " and product.category_id = :categoryId";
	
	public static final String LIMIT_4 = " limit :start, 4 ";
	
}