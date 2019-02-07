package com.nts.reservation.dao;

import static com.nts.reservation.dao.sql.CategoryDaoSqls.SELECT_ALL;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.Category;

/**
 * 카테고리 테이블 접근하는 클래스
 * @author si yoon
 *
 */
@Repository
public class CategoryDao extends BaseDao {
	public CategoryDao(DataSource dataSource) {
		super(dataSource);
	}

	/**
	 * 모든 카테고리 셀렉트하는 메소드
	 * @return 전체 카테고리 목록
	 */
	public List<Category> selectAll() {
		return getJDBC().queryForList(SELECT_ALL, Collections.EMPTY_MAP);
	}
}
