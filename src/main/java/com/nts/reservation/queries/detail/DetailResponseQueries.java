package com.nts.reservation.queries.detail;

public class DetailResponseQueries {
	public static String SELECT_DETAIL = 
		"SELECT display_info.id AS display_info_id,reservation_user_comment.comment,file_info.save_file_name AS comment_image, reservation_info.reservation_name, reservation_info.reservation_date,reservation_user_comment.score,product.description AS product_description, product.content AS product_content, display_info.place_name, display_info.place_lot, display_info.place_street, display_info.tel AS telephone, display_info_image_subquery.display_info_image, product_image_subquery.product_image, average_score_subquery.average_score, average_score_subquery.comment_count" + 
		" FROM display_info" + 
		" INNER JOIN product ON display_info.product_id = product.id" + 
		" LEFT JOIN reservation_info ON display_info.id = reservation_info.display_info_id" + 
		" LEFT JOIN reservation_user_comment ON reservation_info.id = reservation_user_comment.reservation_info_id" + 
		" LEFT JOIN reservation_user_comment_image ON reservation_user_comment.id = reservation_user_comment_image.reservation_user_comment_id" + 
		" LEFT JOIN file_info ON reservation_user_comment_image.file_id = file_info.id" + 
		" INNER JOIN (SELECT display_info.id AS display_info_id, file_info.save_file_name as display_info_image" + 
		" FROM display_info" + 
		" INNER JOIN display_info_image ON display_info.id = display_info_image.display_info_id" + 
		" INNER JOIN file_info ON display_info_image.file_id = file_info.id" + 
		" WHERE display_info.id = :displayInfoId) AS display_info_image_subquery ON display_info.id = display_info_image_subquery.display_info_id" + 
		" INNER JOIN (SELECT display_info.id, file_info.save_file_name AS product_image FROM user4.product_image" + 
		" INNER JOIN file_info ON file_info.id = product_image.file_id" + 
		" INNER JOIN display_info ON product_image.product_id = display_info.product_id" + 
		" WHERE type = 'th') AS product_image_subquery ON display_info.id = product_image_subquery.id" + 
		" INNER JOIN (SELECT display_info.id, ROUND(AVG(reservation_user_comment.score),1) AS average_score, COUNT(reservation_user_comment.id) AS comment_count FROM reservation_user_comment" + 
		" INNER JOIN product ON reservation_user_comment.product_id = product.id" + 
		" INNER JOIN display_info ON product.id = display_info.product_id" + 
		" WHERE display_info.id = :displayInfoId) AS average_score_subquery ON display_info.id = average_score_subquery.id" + 
		" WHERE display_info.id = :displayInfoId" + 
		" ORDER BY reservation_user_comment.id DESC" + 
		" LIMIT :limit";
}
