/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.project3.dao;

import static com.nts.project3.dao.CategoryDaoSqls.*;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.nts.project3.dto.CategoryDto;

/**
 * 카테고리 데이터를 가져오는 클래스  
 * @author jinwoo.bae
 */
@Repository
public class CategoryDao extends BasicDao<CategoryDto> {
	public CategoryDao(DataSource dataSource) {
		super(dataSource);
		setRowMapper(CategoryDto.class);
	}

	/**
	 * 카테고리 목록과 카테고리별 프로덕트의 개수를 구한다. 
	 */
	public List<CategoryDto> selectLimitedListWithProductCount(int limit) {
		return jdbcTemplate.query(SELECT_LIMITED_LIST_WITH_PRODUCT_COUNT, Collections.singletonMap("limit", limit),
			rowMapper);
	}
}