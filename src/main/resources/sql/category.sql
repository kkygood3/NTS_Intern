
-- 카테고리 정보가져오기
SELECT id,name,prd.count as count
FROM category
LEFT JOIN(
SELECT category_id,COUNT(id) as count 
FROM product
GROUP BY category_id) prd
ON category.id = prd.category_id;