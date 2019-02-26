/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.product.dao.query;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public class ProductDaoQuerys {
	public static final String SELECT_ALL_PRODUCT = "SELECT display_info.id AS display_info_id, display_info.place_name, product.content AS product_content, product.description AS product_description, product.id AS product_id, file_info.save_file_name AS product_image_url"
		+ " FROM product"
		+ " LEFT JOIN product_image ON product.id = product_image.product_id"
		+ " LEFT JOIN display_info ON product.id = display_info.product_id"
		+ " INNER JOIN file_info ON product_image.file_id = file_info.id and product_image.type='th'"
		+ " LIMIT :start, :limit";

	public static final String SELECT_PRODUCT_BY_CATEGORY = "SELECT display_info.id AS display_info_id, display_info.place_name, product.content AS product_content, product.description AS product_description, product.id AS product_id, file_info.save_file_name AS product_image_url "
		+ " FROM product"
		+ " INNER JOIN product_image ON product.id = product_image.product_id"
		+ " INNER JOIN display_info ON product.id = display_info.product_id"
		+ " INNER JOIN file_info ON product_image.file_id = file_info.id and product_image.type='th'"
		+ " WHERE category_id = :categoryId "
		+ " LIMIT :start, :limit;";

	public static final String SELECT_ALL_PRODUCT_COUNT = "SELECT COUNT(display_info.id) FROM product"
		+ " INNER JOIN product_image ON product.id = product_image.product_id and product_image.type = 'th'"
		+ " INNER JOIN display_info ON product.id = display_info.product_id";

	public static final String SELECT_PRODUCT_COUNT_BY_CATEGORY = "SELECT COUNT(display_info.id) FROM product"
		+ " INNER JOIN product_image ON product.id = product_image.product_id and product_image.type = 'th'"
		+ " INNER JOIN display_info ON product.id = display_info.product_id"
		+ " WHERE product.category_id = :categoryId"
		+ " GROUP BY product.category_id";

	public static final String SELECT_PRODUCT_IMAGE = "SELECT file_info.content_type 'contentType', file_info.create_date 'createDate', file_info.delete_flag 'deleteFlag', file_info.id 'fileInfoId', file_info.file_name 'fileName', file_info.modify_date 'modifyDate', display_info.product_id 'productId', product_image.id 'productImageId', file_info.save_file_name 'saveFileName', product_image.type 'type'"
		+ " FROM display_info"
		+ " INNER JOIN product_image ON display_info.product_id = product_image.product_id"
		+ " INNER JOIN file_info ON product_image.file_id = file_info.id"
		+ " WHERE display_info.id = :displayInfoId";

	public static final String SELECT_PRODUCT_PRICE = "SELECT product_price.create_date 'createDate', product_price.discount_rate 'discountRate', product_price.modify_date 'modifyDate', product_price.price 'price', product_price.price_type_name 'priceTypeName', product_price.product_id 'productId', product_price.id 'productPriceId'"
		+ " FROM display_info"
		+ " INNER JOIN product ON display_info.product_id = product.id"
		+ " INNER JOIN product_price ON product.id = product_price.product_id"
		+ " WHERE display_info.id = :displayInfoId";

	public static final String SELECT_PRODUCT_EXTRA_IMAGE = "SELECT display_info.id, file_info.save_file_name AS product_image, product.description as product_description FROM product_image"
		+ " INNER JOIN file_info ON file_info.id = product_image.file_id"
		+ " INNER JOIN display_info ON product_image.product_id = display_info.product_id"
		+ " INNER JOIN product ON display_info.product_id = product.id"
		+ " WHERE type = 'et' AND display_info.id = :displayInfoId"
		+ " GROUP BY display_info.id";
}
