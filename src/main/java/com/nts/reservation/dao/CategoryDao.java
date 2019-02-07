package com.nts.reservation.dao;

import static com.nts.reservation.dao.sql.CategoryDaoSqls.SELECT_ALL;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.Category;

/*
 * 전체 카테고리 리스트 셀렉트
 * @author 시윤
 */
@Repository
public class CategoryDao extends BaseDao {
	public CategoryDao(DataSource dataSource) {
		super(dataSource);
	}

	public List<Category> selectAll() {
		return getJDBC().queryForList(SELECT_ALL, Collections.EMPTY_MAP);
	}
}
