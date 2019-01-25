SELECT * 
FROM 
	(SELECT 
		d.id AS display_info_id,
		d.place_name AS place_name,
		p.content AS product_content,
		p.description AS product_description,
		d.product_id AS product_id    
	FROM display_info d INNER JOIN product p
	ON p.id = d.product_id
	WHERE p.category_id LIKE '%3%' LIMIT 4) prd

	LEFT JOIN (SELECT product_id, file_id
					FROM product_image 
					WHERE product_image.type = 'th'
			  ) AS prd_img ON prd_img.product_id = prd.product_id;
