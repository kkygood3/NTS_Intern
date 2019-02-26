/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.product.dao.impl;

import static com.nts.reservation.product.dao.queries.ProductQueries.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.product.dao.ProductDao;
import com.nts.reservation.product.dto.ImageType;
import com.nts.reservation.product.dto.Product;
import com.nts.reservation.product.dto.ProductImage;
import com.nts.reservation.product.dto.ProductPrice;

@Repository
public class ProductDaoImpl implements ProductDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Product> rowMapperProduct = BeanPropertyRowMapper.newInstance(Product.class);
	private RowMapper<ProductPrice> rowMapperProductPrice = BeanPropertyRowMapper.newInstance(ProductPrice.class);
	private RowMapper<ProductImage> rowMapperProductImage =
		new RowMapper<ProductImage>() {
				@Override
				public ProductImage mapRow(ResultSet rs, int rowNum) throws SQLException {
					ProductImage user = new ProductImage();
				user.setContentType(rs.getString("content_type"));
				user.setCreateDate(rs.getString("create_date"));
				user.setDeleteFlag(rs.getBoolean("delete_flag"));
				user.setFileInfoId(rs.getInt("file_info_id"));
				user.setFileName(rs.getString("file_name"));
				user.setModifyDate(rs.getString("modify_date"));
				user.setProductId(rs.getInt("product_id"));
				user.setProductImageId(rs.getInt("product_image_id"));
				user.setSaveFileName(rs.getString("save_file_name"));
				user.setType(ImageType.valueOf(rs.getString("type").toUpperCase()));
				return user;
			}
		};

	public ProductDaoImpl(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<Product> selectPagingProducts(int start, int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(SELECT_PROUDUCT_PAGE, params, rowMapperProduct);
	}

	@Override
	public List<Product> selectPagingProductsByCategoryId(int categoryId, int start, int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("categoryId", categoryId);
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(SELECT_PROUDUCT_PAGE_BY_CATEGORY, params, rowMapperProduct);
	}

	@Override
	public int selectCount() {
		return jdbc.queryForObject(SELECT_PRODUCT_COUNT, Collections.emptyMap(), Integer.class);
	}

	@Override
	public int selectCountByCategory(int categoryId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("categoryId", categoryId);
		return jdbc.queryForObject(SELECT_PRODUCT_COUNT_BY_CATEGORY, params, Integer.class);
	}

	@Override
	public List<ProductImage> selectProductImages(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);

		return jdbc.query(SELECT_PROUDUCT_IMAGE, params, rowMapperProductImage);
	}

	@Override
	public List<ProductPrice> selectProductPrices(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		return jdbc.query(SELECT_PROUDUCT_PRICE, params, rowMapperProductPrice);
	}


}
