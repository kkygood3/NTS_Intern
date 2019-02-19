/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.productdisplay.dao.querys;

public class ProductDisplayQuerys {

	private static final int LIMIT_COUNT = 2;

	public static final String SELECT_PRODUCT_DISPLAY = "select p.id as product_id"
		+ ", p.description as product_description"
		+ ", p.content as product_content"
		+ ", d.id as display_info_id"
		+ ", d.place_name"
		+ ", d.place_lot"
		+ ", d.place_street"
		+ ", d.tel as telephone "
		+ ", f.save_file_name as display_info_image_url "
		+ "from product p "
		+ "inner join display_info d "
		+ "on p.id = d.product_id "
		+ "inner join display_info_image i "
		+ "on d.id = i.display_info_id "
		+ "inner join file_info f "
		+ "on i.file_id = f.id "
		+ "where d.id = :displayInfoId";

	public static final String SELECT_PRODUCT_DISPLAY_IMAGE_URL_LIST = "select f.save_file_name "
		+ "from display_info d "
		+ "inner join product_image i "
		+ "on d.product_id = i.product_id "
		+ "inner join file_info f "
		+ "on i.file_id = f.id "
		+ "where i.type in ('ma', 'et') "
		+ "and d.id = :displayInfoId "
		+ "limit " + LIMIT_COUNT;
}
