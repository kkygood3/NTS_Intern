package com.nts.reservation.dao.sqls;

public class DetailDaoSqls {
	public static final String SELECT_DETAIL_DISPLAY_INFO = "SELECT pdt.id AS productId"
		+ ", category_id AS categoryId"
		+ ", di.id AS displayInfoId"
		+ ", ctg.name AS categoryName"
		+ ", pdt.description AS productdescription"
		+ ", pdt.content AS productContent"
		+ ", pdt.event AS productEvent"
		+ ", di.opening_hours AS openingHours"
		+ ", di.place_name as placeName"
		+ ", di.place_lot as placeLot"
		+ ", di.place_street AS placeStreet"
		+ ", di.homepage, di.tel AS telephone"
		+ ", di.email, di.create_date AS createDate"
		+ ", di.modify_date AS modifyDate"
		+ " FROM  display_info di "
		+ " INNER JOIN product pdt ON pdt.id = di.product_id AND di.id = :display_info_id"
		+ " INNER JOIN category ctg ON pdt.category_id = ctg.id";
	
	public static final String SELECT_DETAIL_PRODUCT_IMAGES = "SELECT di.product_id AS productId"
		+ ", pi.id AS productImageId"
		+ ", pi.type AS type"
		+ ", fi.id AS fileInfoId"
		+ ", fi.file_name AS fileName"
		+ ", fi.save_file_name AS saveFileName"
		+ ", fi.content_type AS contentType"
		+ ", fi.delete_flag AS deleteFlag"
		+ ", fi.create_date AS createDate"
		+ ", fi.modify_date AS modifyDate"
		+ " FROM display_info di"
		+ " INNER JOIN product_image pi ON di.product_id = pi.product_id AND di.id = :display_info_id"
		+ " INNER JOIN file_info fi ON fi.id = pi.file_id AND pi.type <> 'th' ";

	public static final String SELECT_DETAIL_DISPLAY_INFO_IMAGE = "SELECT dii.id AS displayInfoImageId"
		+ ", di.id AS displayInfoId"
		+ ", fi.id AS fileId"
		+ ", fi.file_name AS fileName"
		+ ", fi.save_file_name AS saveFileName"
		+ ", fi.content_type AS contentType"
		+ ", fi.delete_flag AS deleteFlag"
		+ ", fi.create_date AS createDate"
		+ ", fi.modify_date AS modifyDate"
		+ " FROM display_info di "
		+ " INNER JOIN display_info_image dii ON di.product_id = dii.display_info_id AND di.id = :display_info_id"
		+ " INNER JOIN file_info fi ON dii.file_id = fi.id";

	public static final String SELECT_DETAIL_COMMENTS = "SELECT ruc.id AS commentId "
		+ ", di.product_id AS productId "
		+ ", ri.id AS reservationInfoId"
		+ ", IFNULL(CONVERT( ruc.score, DECIMAL(2,1)), 0 ) AS score"
		+ ", ruc.comment AS comment "
		+ ", ri.reservation_name AS reservationName"
		+ ", ri.reservation_email AS reservationEmail"
		+ ", ri.reservation_date AS reservationDate"
		+ ", ri.create_date AS createDate"
		+ ", ri.modify_date AS modifyDate"
		+ " FROM reservation_user_comment ruc"
		+ " INNER JOIN display_info di ON di.product_id = ruc.product_id AND di.id = :display_info_id"
		+ " INNER JOIN reservation_info ri ON ri.id = ruc.reservation_info_id"
		+ " ORDER BY ruc.id DESC";
	
	

	public static final String SELECT_DETAIL_COMMENTS_AVERAGE_SCORE = "SELECT IFNULL(CONVERT( AVG(ruc.score), DECIMAL(3,1)), 0 ) as average"
		+ " FROM display_info di "
		+ " INNER JOIN reservation_user_comment ruc ON ruc.product_id = di.product_id AND di.id = :display_info_id";

	public static final String SELECT_DETAIL_COMMENTS_IMAGES = "SELECT ruci.id as imageId"
		+ ", ruc.reservation_info_id as reservationInfoId"
		+ ", fi.id as fileId" + ", fi.file_name as fileName"
		+ ", fi.save_file_name as saveFileName"
		+ ", fi.content_type as contentType"
		+ ", fi.delete_flag as deleteFlag"
		+ ", fi.create_date as createDate"
		+ ", fi.modify_date as modifyDate"
		+ " FROM reservation_user_comment ruc"
		+ " INNER JOIN display_info di ON di.product_id = ruc.product_id AND di.id = :display_info_id"
		+ " INNER JOIN reservation_user_comment_image ruci ON ruc.id = ruci.reservation_user_comment_id"
		+ " INNER JOIN file_info fi ON ruci.file_id = fi.id"
		+ " ORDER BY ruc.id DESC";

	public static final String SELECT_DETAIL_COMMENTS_PRODUCT_PRICES = "SELECT pp.id AS productPriceId"
		+ ", pdt.id AS productId"
		+ ", pp.price_type_name AS priceTypeName"
		+ ", pp.price AS price"
		+ ", pp.discount_rate AS discountRate"
		+ ", pp.create_date AS createDate"
		+ ", pp.modify_date AS modifyDate"
		+ " FROM product_price pp"
		+ " INNER JOIN product pdt ON pdt.id = pp.product_id"
		+ " INNER JOIN display_info di ON di.product_id = pdt.id AND di.id = :display_info_id"
		+ " ORDER BY pp.id DESC";

}
