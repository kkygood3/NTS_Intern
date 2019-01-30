/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.dao;


/**
 *
 * @description : PromotionDaoSqls
 * @package : com.nts.dao
 * @filename : PromotionDaoSqls.java
 * @author : 최석현
 * 
 */
public class PromotionDaoSqls {
	public static final String SELECT_ALL = "SELECT promotion.id, promotion.product_id, "
												+ "save_file_name as product_image_url "
											+ "FROM product, promotion, product_image, file_info "
											+ "WHERE product_image.type = 'th' "
												+ "and product.id = promotion.product_id "
												+ "and product.id = product_image.product_id "
												+ "and file_info.id = product_image.file_id "
											+ "ORDER BY promotion.id asc";

}
