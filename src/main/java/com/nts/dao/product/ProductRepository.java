/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.dao.product;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.dto.product.Product;
import com.nts.dto.product.ProductImage;
import com.nts.dto.product.ProductPrice;

import static com.nts.sqls.product.ProductSqls.*;

/**
 * @author 전연빈
 */
@Repository
public class ProductRepository {

	private static final int LIMIT = 4;
	private static final int PRODUCT_IMAGE_LIMIT = 2;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private RowMapper<Product> productRowMapper = BeanPropertyRowMapper.newInstance(Product.class);
	private RowMapper<ProductImage> productImageRowMapper = BeanPropertyRowMapper.newInstance(ProductImage.class);
	private RowMapper<ProductPrice> productPriceRowMapper = BeanPropertyRowMapper.newInstance(ProductPrice.class);

	/**
	 * @desc 카테고리별 데이터 가져오기
	 * @param categoryId
	 * @param start
	 * @return list(Product)
	 */
	public List<Product> selectProductsByCategory(int categoryId, int start) {

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("categoryId", categoryId);
		params.put("start", start * LIMIT);
		params.put("limit", LIMIT);

		return namedParameterJdbcTemplate.query(SELECT_PRODUCTS_BY_CATEGORY, params, productRowMapper);
	}

	/**
	 * @desc 카테고리 별 카운트 갯수
	 * @param categoryId
	 * @return totalCount
	 */
	public int selectProductsCountByCategory(int categoryId) {

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("categoryId", categoryId);

		return namedParameterJdbcTemplate.queryForObject(SELECT_PRODUCTS_COUNT_BY_CATEGORY, params, Integer.class);
	}

	/**
	 * @desc 상품 전체 가져오기
	 * @param start
	 * @return list(Product)
	 */
	public List<Product> selectProducts(int start) {

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("start", start * LIMIT);
		params.put("limit", LIMIT);

		return namedParameterJdbcTemplate.query(SELECT_PRODUCTS_ALL, params, productRowMapper);
	}

	/**
	 * @desc 상품 총 카운트 갯수
	 * @return totalCount
	 */
	public int selectProductsCount() {

		return namedParameterJdbcTemplate.queryForObject(SELECT_PRODUCTS_COUNT_ALL, Collections.emptyMap(),Integer.class);
	}

	/**
	 * @desc product Id 별 product Image 리스트 불러오기
	 * @param productId
	 * @return productImage list
	 */
	public List<ProductImage> selectProductImagesByProductId(int productId) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("limit", PRODUCT_IMAGE_LIMIT);
		params.put("productId", productId);

		return namedParameterJdbcTemplate.query(SELECT_PRODUCT_IMAGES_LIMIT, params, productImageRowMapper);
	}

	/**
	 * @desc product Id로 상품 가격 조회
	 * @param productId
	 * @return list productPrice
	 */
	public List<ProductPrice> selectProductPricesById(int productId) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("productId", productId);
		
		return namedParameterJdbcTemplate.query(SELECT_PRODUCT_PRICES_BY_PRODUCT_ID, params, productPriceRowMapper);
	}

}
