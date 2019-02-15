package com.nts.reservation.dao;

import static com.nts.reservation.dao.sql.ProductDaoSqls.SELECT_COUNT_ALL;
import static com.nts.reservation.dao.sql.ProductDaoSqls.SELECT_COUNT_BY_CATEGORY;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

/**
 * 상품 테이블 접근하는 클래스
 * @author si yoon
 *
 */
@Repository
public class ProductDao extends BaseDao {

	public ProductDao(DataSource dataSource) {
		super(dataSource);
	}

	/**
	 * 모든 상품수 리턴하는 메소드
	 * @return 전체 상품 수
	 */
	public Integer selectCount() {
		return getJdbc().queryForObject(SELECT_COUNT_ALL, Collections.EMPTY_MAP, Integer.class);
	}

	/**
	 * 선택된 카테고리의 상품수 리턴하는 메소드
	 * @param categoryId 카테고리 아이디
	 * @return 해당 카테고리의 상품 수
	 */
	public Integer selectCount(int categoryId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("categoryId", categoryId);
		return getJdbc().queryForObject(SELECT_COUNT_BY_CATEGORY, params, Integer.class);
	}
}
