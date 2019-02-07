
-- 카테고리 정보가져오기
SELECT c.id AS id, c.name AS name,COUNT(c.id) AS count 
FROM display_info d INNER JOIN product p INNER JOIN category c
ON d.product_id = p.id AND p.category_id = c.id
GROUP BY c.id