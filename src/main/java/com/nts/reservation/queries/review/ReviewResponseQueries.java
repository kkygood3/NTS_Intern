package com.nts.reservation.queries.review;

public class ReviewResponseQueries {
	public static String SELECT_REVIEW = 
		"SELECT display_info.id AS display_info_id,reservation_user_comment.comment,file_info.save_file_name AS comment_image, reservation_info.reservation_name, DATE_FORMAT(reservation_info.reservation_date,'%Y.%c.%d') AS reservation_date,reservation_user_comment.score,product.description AS product_description, average_score_subquery.average_score, average_score_subquery.comment_count" + 
		" FROM display_info " + 
		" LEFT OUTER JOIN reservation_info ON display_info.id = reservation_info.display_info_id " + 
		" LEFT OUTER JOIN reservation_user_comment ON reservation_info.id = reservation_user_comment.reservation_info_id " + 
		" LEFT JOIN reservation_user_comment_image ON reservation_user_comment.id = reservation_user_comment_image.reservation_user_comment_id " + 
		" LEFT JOIN product ON display_info.product_id = product.id " + 
		" LEFT JOIN file_info ON reservation_user_comment_image.file_id = file_info.id" + 
		
		" INNER JOIN (SELECT display_info.id, ROUND(AVG(reservation_user_comment.score),1)  AS average_score, COUNT(reservation_user_comment.id) AS comment_count FROM reservation_user_comment " + 
		" INNER JOIN product ON reservation_user_comment.product_id = product.id " + 
		" INNER JOIN display_info ON product.id = display_info.id " + 
		" WHERE display_info.id = :displayInfoId) AS average_score_subquery ON display_info.id = average_score_subquery.id" +
		
		" WHERE display_info.id = :displayInfoId " + 
		" ORDER BY reservation_user_comment.id DESC " + 
		" LIMIT :pagingLimit";
}
