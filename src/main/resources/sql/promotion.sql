
-- promotion 정보가져오기
SELECT prom.id,prom.product_id as product_id,file_info.save_file_name as save_file_name
FROM file_info
RIGHT JOIN (SELECT pr_image.file_id, pr_image.product_id, prm.id
FROM promotion AS prm
LEFT JOIN 
(SELECT product_id, file_id
FROM product_image 
WHERE product_image.type = 'th') AS pr_image
ON prm.product_id = pr_image.product_id) AS prom
ON file_info.id = prom.file_id
WHERE delete_flag = 0