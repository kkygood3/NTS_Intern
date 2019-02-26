/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.reservation.dao.query;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public class ReservationDaoQuerys {
	public static final String INSERT_RESERVATION_INFO = " INSERT" +
		" INTO reservation_info(" +
		" cancel_flag," +
		" create_date," +
		" display_info_id," +
		" modify_date," +
		" product_id," +
		" reservation_date," +
		" reservation_email," +
		" reservation_name," +
		" reservation_tel" +
		" )" +
		" SELECT" +
		" 0," +
		" NOW()," +
		" display_info.id," +
		" NOW()," +
		" product.id," +
		" :reservationDate," +
		" :reservationEmail," +
		" :reservationName," +
		" :reservationTel" +
		" FROM display_info" +
		" INNER JOIN product ON display_info.product_id = product.id" +
		" WHERE display_info.id = :displayInfoId" +
		" GROUP BY product.id";

	public static final String INSERT_RESERVATION_PRICE = " INSERT" +
		" INTO reservation_info_price(" +
		" reservation_info_id," +
		" product_price_id," +
		" count" +
		" )" +
		" SELECT" +
		" :reservationInfoId," +
		" product_price.id," +
		" :count" +
		" FROM display_info" +
		" INNER JOIN product_price ON display_info.product_id = product_price.product_id" +
		" WHERE product_price.price_type_name = :type AND display_info.id = :displayInfoId";

	//	public static final String SELECT_RESERVATION_PRICE = " SELECT" +
	//		" product_price.id 'productPriceId'," +
	//		" reservation_info.id 'reservationInfoId'," +
	//		" reservation_info_price.id 'reservationInfoPriceId'," +
	//		" product_price.price_type_name 'priceTypeName'," +
	//		" product_price.discount_rate 'discountRate'," +
	//		" reservation_info_price.count 'count'" +
	//		" FROM display_info" +
	//		" INNER JOIN product_price ON product_price.product_id = display_info.product_id" +
	//		" INNER JOIN reservation_info_price ON reservation_info_price.product_price_id = product_price.id" +
	//		" INNER JOIN reservation_info ON reservation_info.id = reservation_info_price.id" +
	//		" WHERE display_info.id = :displayInfoId" +
	//		" GROUP BY reservation_info.id";

	public static final String SELECT_RESERVATION_PRICE = " SELECT" +
		" product_price.id 'productPriceId'," +
		" product_price.price_type_name 'priceTypeName'," +
		" product_price.discount_rate 'discountRate'," +
		" product_price.price 'price'" +
		" FROM display_info" +
		" INNER JOIN product_price ON product_price.product_id = display_info.product_id" +
		" WHERE display_info.id = :displayInfoId";

	public static final String SELECT_RESERVATION_DISPLAY_INFO = " SELECT" +
		" display_info.id 'displayInfoId'," +
		" display_info.opening_hours 'openingHours'," +
		" display_info.place_name 'placeName'," +
		" display_info.place_street 'placeStreet'," +
		" file_info.save_file_name 'productImage'," +
		" product.description 'productDescription'" +
		" FROM display_info" +
		" INNER JOIN product ON product.id = display_info.product_id" +
		" INNER JOIN product_image ON product_image.product_id = product.id AND product_image.type = 'ma'" +
		" INNER JOIN file_info ON file_info.id = product_image.file_id" +
		" WHERE display_info.id = :displayInfoId";

	public static final String CANCEL_RESERVATION = " UPDATE" +
		" reservation_info" +
		" SET" +
		" cancel_flag = 1" +
		" WHERE reservation_info.id = :reservationInfoId";

	public static final String SELECT_CONFIRM_RESERVATION_INFO = " SELECT" +
		" reservation_info.id 'reservationInfoId'," +
		" reservation_info_price.id 'reservationInfoPriceId'," +
		" reservation_info.reservation_name 'reservationName'," +
		" reservation_info.display_info_id 'displayInfoId'," +
		" reservation_info.reservation_date 'reservationDate'," +
		" product.description 'productDescription'," +
		" display_info.place_name 'placeName'," +
		" display_info.opening_hours 'openingHours'," +
		" SUM(ROUND(product_price.price * (100-product_price.discount_rate) * reservation_info_price.count/100,0)) 'price',"
		+
		" reservation_info.cancel_flag 'cancelFlag'" +
		" FROM reservation_info" +
		" INNER JOIN display_info ON reservation_info.display_info_id = display_info.id" +
		" INNER JOIN product ON reservation_info.product_id = product.id" +
		" INNER JOIN reservation_info_price ON reservation_info.id = reservation_info_price.reservation_info_id" +
		" INNER JOIN product_price ON reservation_info_price.product_price_id = product_price.id" +
		" WHERE reservation_info.reservation_email = :reservationEmail" +
		" AND reservation_info.cancel_flag = 0" +
		" AND NOW() < reservation_info.reservation_date" +
		" GROUP BY reservation_info.id" +
		" LIMIT :start, :limit";

	public static final String SELECT_COMPLETE_RESERVATION_INFO = " SELECT" +
		" reservation_info.id 'reservationInfoId'," +
		" reservation_info_price.id 'reservationInfoPriceId'," +
		" reservation_info.reservation_name 'reservationName'," +
		" reservation_info.display_info_id 'displayInfoId'," +
		" reservation_info.reservation_date 'reservationDate'," +
		" product.description 'productDescription'," +
		" display_info.place_name 'placeName'," +
		" display_info.opening_hours 'openingHours'," +
		" SUM(ROUND(product_price.price * (100-product_price.discount_rate) * reservation_info_price.count/100,0)) 'price',"
		+
		" reservation_info.cancel_flag 'cancelFlag'" +
		" FROM reservation_info" +
		" INNER JOIN display_info ON reservation_info.display_info_id = display_info.id" +
		" INNER JOIN product ON reservation_info.product_id = product.id" +
		" INNER JOIN reservation_info_price ON reservation_info.id = reservation_info_price.reservation_info_id" +
		" INNER JOIN product_price ON reservation_info_price.product_price_id = product_price.id" +
		" WHERE reservation_info.reservation_email = :reservationEmail" +
		" AND reservation_info.cancel_flag = 0" +
		" AND NOW() >= reservation_info.reservation_date" +
		" GROUP BY reservation_info.id" +
		" LIMIT :start, :limit";

	public static final String SELECT_CANCEL_RESERVATION_INFO = " SELECT" +
		" reservation_info.id 'reservationInfoId'," +
		" reservation_info_price.id 'reservationInfoPriceId'," +
		" reservation_info.reservation_name 'reservationName'," +
		" reservation_info.display_info_id 'displayInfoId'," +
		" reservation_info.reservation_date 'reservationDate'," +
		" product.description 'productDescription'," +
		" display_info.place_name 'placeName'," +
		" display_info.opening_hours 'openingHours'," +
		" SUM(ROUND(product_price.price * (100-product_price.discount_rate) * reservation_info_price.count/100,0)) 'price',"
		+
		" reservation_info.cancel_flag 'cancelFlag'" +
		" FROM reservation_info" +
		" INNER JOIN display_info ON reservation_info.display_info_id = display_info.id" +
		" INNER JOIN product ON reservation_info.product_id = product.id" +
		" INNER JOIN reservation_info_price ON reservation_info.id = reservation_info_price.reservation_info_id" +
		" INNER JOIN product_price ON reservation_info_price.product_price_id = product_price.id" +
		" WHERE reservation_info.reservation_email = :reservationEmail" +
		" AND reservation_info.cancel_flag = 1" +
		" GROUP BY reservation_info.id" +
		" LIMIT :start, :limit";

	public static final String SELECT_CONFIRM_RESERVATION_INFO_COUNT = " SELECT" +
		" COUNT(reservation_info.id) 'count'" +
		" FROM reservation_info" +
		" WHERE reservation_info.reservation_email = :reservationEmail" +
		" AND reservation_info.cancel_flag = 0" +
		" AND NOW() < reservation_info.reservation_date";

	public static final String SELECT_COMPLETE_RESERVATION_INFO_COUNT = " SELECT" +
		" COUNT(reservation_info.id) 'count'" +
		" FROM reservation_info" +
		" WHERE reservation_info.reservation_email = :reservationEmail" +
		" AND reservation_info.cancel_flag = 0" +
		" AND NOW() >= reservation_info.reservation_date";

	public static final String SELECT_CANCEL_RESERVATION_INFO_COUNT = " SELECT" +
		" COUNT(reservation_info.id) 'count'" +
		" FROM reservation_info" +
		" WHERE reservation_info.reservation_email = :reservationEmail" +
		" AND reservation_info.cancel_flag = 1";
}
