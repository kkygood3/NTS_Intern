/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.sqls.product;

/**
 * @author 전연빈
 */
public class ProductSqls {
	public static final String SELECT_PRODUCTS_BY_CATEGORY =
	"SELECT d.id AS display_info_id, d.place_name AS place_name,p.content AS product_content, p.description AS product_description, p.id AS product_id, f.save_file_name AS product_image_url " + 
	"FROM  display_info d INNER JOIN product p INNER JOIN product_image p_img INNER JOIN file_info f " + 
	"ON d.product_id = p.id AND d.product_id = p_img.product_id AND p_img.file_id = f.id " + 
	"WHERE p_img.type ='th' AND p.category_id = :categoryId " + 
	"limit :start,:limit";
	
	public static final String SELECT_PRODUCTS_COUNT_BY_CATEGORY =
	"SELECT count(d.id) " + 
	"FROM product p INNER JOIN display_info d " + 
	"ON p.id = d.product_id " + 
	"WHERE p.category_id = :categoryId";
	
	public static final String SELECT_PRODUCTS_ALL =
	"SELECT d.id AS display_info_id, d.place_name AS place_name,p.content AS product_content, p.description AS product_description, p.id AS product_id, f.save_file_name AS product_image_url " + 
	"FROM  display_info d INNER JOIN product p INNER JOIN product_image p_img INNER JOIN file_info f " + 
	"ON d.product_id = p.id AND d.product_id = p_img.product_id AND p_img.file_id = f.id " + 
	"WHERE p_img.type ='th' " + 
	"limit :start,:limit";
	
	public static final String SELECT_PRODUCTS_COUNT_ALL = 
	"SELECT count(d.id) " + 
	"FROM product p INNER JOIN display_info d " + 
	"ON p.id = d.product_id ";
	
	public static final String SELECT_PRODUCT_IMAGES_LIMIT = 
	"SELECT pr_img.product_id AS product_id, pr_img.id AS product_image_Id, pr_img.type AS type, pr_img.file_id AS file_info_id, f.file_name AS file_name, f.save_file_name AS save_file_name, f.content_type AS content_type, IF(f.delete_flag,'true','false') AS delete_flag, f.create_date AS create_date, f.modify_date AS modify_date " + 
	"FROM product_image pr_img INNER JOIN file_info f " + 
	"ON pr_img.file_id = f.id  " + 
	"WHERE pr_img.product_id = :productId AND (type = 'ma' OR type = 'et') " + 
	"LIMIT :limit";
	
	public static final String SELECT_PRODUCT_PRICES_BY_PRODUCT_ID =
	"SELECT id AS product_price_id,product_id,price_type_name,price,FLOOR(discount_rate) AS discount_rate,create_date,modify_date " + 
	"FROM product_price " + 
	"WHERE product_id = :productId " + 
	"ORDER BY id DESC";
}
