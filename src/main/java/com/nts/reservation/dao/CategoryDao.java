package com.nts.reservation.dao;

import static com.nts.reservation.dao.sql.CategoryDaoSqls.SELECT_ALL;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.Category;

/*
 * 전체 카테고리 리스트 셀렉트
 * @author 시윤
 */
@Repository
public class CategoryDao {
	private NamedParameterJdbcTemplate jdbc;

	public CategoryDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Category> selectAll() {
		return jdbc.queryForList(SELECT_ALL, Collections.EMPTY_MAP);
	}
}
