/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao;

import static com.nts.reservation.dao.query.CategoryDaoQuerys.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.Category;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@Repository
public class CategoryDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<Category> rowMapper = BeanPropertyRowMapper.newInstance(Category.class);

	public CategoryDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		//        this.insertAction = new SimpleJdbcInsert(dataSource)
		//                .withTableName("guestbook")
		//                .usingGeneratedKeyColumns("id");
	}

	public List<Category> selectAllCategories(Integer limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("limit", limit);
		return jdbc.query(SELECT_CATEGORY, params, rowMapper);
	}

	//	public Long insert(Category guestbook) {
	//		SqlParameterSource params = new BeanPropertySqlParameterSource(guestbook);
	//		return insertAction.executeAndReturnKey(params).longValue();
	//	}
	//
	//	public int deleteById(Long id) {
	//		Map<String, ?> params = Collections.singletonMap("id", id);
	//		return jdbc.update(DELETE_BY_ID, params);
	//	}
	//
	//	public int selectCount() {
	//		return jdbc.queryForObject(SELECT_COUNT, Collections.emptyMap(), Integer.class);
	//	}
}
