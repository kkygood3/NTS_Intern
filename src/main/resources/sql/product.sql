SELECT d.id AS display_id, d.place_name AS place_name,p.content AS product_content, p.description AS product_content, p.id AS product_id, f.save_file_name AS product_image_url
FROM  display_info d INNER JOIN product p INNER JOIN product_image p_img INNER JOIN file_info f
ON d.product_id = p.id AND d.product_id = p_img.product_id AND p_img.file_id = f.id
WHERE p_img.type ='th' AND p.category_id LIKE '%0%'
limit 0,4;