/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.dao.productdao;


/**
 *
 * @description : ProductDaoSqls
 * @package : com.nts.dao.productdao
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
														+ ", place_name  "
														+ ", content "
														+ ", description  "
														+ ", display_info.product_id AS id "
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
	
	public static final String SELECT_PRODUCT_IMAGES_BY_DISPLAY_INFO_ID = "SELECT "
																+ "  file_info.content_type "
																+ ", file_info.create_date "
																+ ", file_info.delete_flag "
																+ ", file_info.id as file_info_id "
																+ ", file_info.file_name "
																+ ", file_info.modify_date "
																+ ", product.id as product_id "
																+ ", product_image.id as product_image_id "
																+ ", file_info.save_file_name as product_image_url "
																+ ", product_image.type "
															+ "FROM "
																+ "  file_info "
																+ ", product "
																+ ", product_image "
																+ ", display_info "
															+ "WHERE "
																+ "    display_info.id = :displayInfoId "
																+ "and product.id = display_info.product_id "
																+ "and product.id = product_image.product_id "
																+ "and file_info.id = product_image.file_id "
																+ "and (product_image.type = 'ma' OR product_image.type = 'et') "
																+ "LIMIT 2";
	
	 static final String SELECT_PRODUCT_PRICES_BY_DISPLAY_INFO_ID = "SELECT "
			 											+ "  product_price.create_date "
			 											+ ", product_price.discount_rate "
			 											+ ", product_price.modify_date "
			 											+ ", product_price.price "
			 											+ ", product_price.price_type_name "
			 											+ ", product_price.product_id "
			 											+ ", product_price.id "
			 										 + "FROM "
			 										 	+ "  product "
			 										 	+ ", product_price "
			 										 	+ ", display_info "
			 										 + "WHERE "
			 											+ "    display_info.id = :displayInfoId "
			 											+ "and product.id = display_info.product_id "
			 											+ "and product.id = product_price.id ";

	
}