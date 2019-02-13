/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.dao.promotiondao;


/**
 *
 * @description : PromotionDaoSqls
 * @package : com.nts.dao.promotiondao
 * @filename : PromotionDaoSqls.java
 * @author : 최석현
 * 
 */
public class PromotionDaoSqls {
	public static final String SELECT_PROMOTIONS_ALL = "SELECT "
														+ "promotion.id AS id"
														+ ", promotion.product_id AS product_id"
														+ ", save_file_name AS product_image_url "
													 + "FROM "
													 	+ "product"
													 	+ ", promotion"
													 	+ ", product_image"
													 	+ ", file_info "
													 + "WHERE product_image.type = 'th' "
													 	+ "AND product.id = promotion.product_id "
													 	+ "AND product.id = product_image.product_id "
													 	+ "AND file_info.id = product_image.file_id "
													 + "ORDER BY "
													 	+ "promotion.id "
													 + "ASC";

}
