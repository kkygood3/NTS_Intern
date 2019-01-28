package com.nts.reservation.dao;

public class ProductDaoSqls {
	public static final String SELECT_PROMOTION = "SELECT promotion.id as id, promotion.product_id as productId, CONCAT('img/',product_image.product_id,'_',product_image.type,'_',product_image.id,'.png') as productImageUrl FROM promotion INNER JOIN product_image ON promotion.product_id = product_image.product_id Group by id";
	public static final String SELECT_PROMOTION_COUNT = "SELECT count(*) FROM promotion";
	public static final String SELECT_ALL_PRODUCTS_BY_CATEGORY = "SELECT di.id AS displayInfoId,di.product_id AS productId,p1.description AS productDescription,di.place_name AS placeName, p1.content AS productContent FROM product p1 JOIN display_info di WHERE p1.id = di.product_id AND p1.category_id =:category_id";

	public static final String SELECT_ALL_PRODUCTS = "SELECT di.id AS displayInfoId,di.product_id AS productId,p1.description AS productDescription,di.place_name AS placeName,p1.content AS productContent,  CONCAT('img/',pi.product_id,'_',pi.type,'_',pi.id,'.png') as productImageUrl FROM product p1 JOIN display_info di JOIN product_image pi WHERE p1.id = di.product_id AND di.product_id = pi.product_id GROUP BY displayInfoId;";

	public static final String SELECT_BY_ID = "SELECT * FROM product WHERE id = :id";
	public static final String SELECT_PRODUCT_WITH_DISPLAY_INFO_ID = "SELECT di.id AS displayInfoId,di.product_id AS productId,p1.description AS productDescription,di.place_name AS placeName, p1.content AS productContent FROM product p1 JOIN display_info di WHERE p1.id = di.product_id AND di.id =:display_info_id";
	public static final String SELECT_COUNT_BY_CATEGORY = "SELECT count(*) FROM product WHERE category_id = :categoryId";

}
