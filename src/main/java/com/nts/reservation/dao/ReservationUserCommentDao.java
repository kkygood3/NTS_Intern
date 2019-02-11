package com.nts.reservation.dao;

import static com.nts.reservation.dao.sql.ReservationUserCommentDaoSqls.SELECT_AVERAGE_SCORE_BY_ID;
import static com.nts.reservation.dao.sql.ReservationUserCommentDaoSqls.SELECT_COUNT_BY_ID;
import static com.nts.reservation.property.Const.PRODUCT_ID;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

@Repository
public class ReservationUserCommentDao extends BaseDao {
	public ReservationUserCommentDao(DataSource dataSource) {
		super(dataSource);
	}
	
	public Double selectAverageScore(long productId) {
		Map<String, Long> params = new HashMap<>();
		params.put(PRODUCT_ID, productId);
		Double averageScore = getJdbc().queryForObject(SELECT_AVERAGE_SCORE_BY_ID,  params, Double.class);
		if (averageScore == null) {
			return 0.0;
		} else {
			return averageScore;
		}
	}
	
	/**
	 * 모든 리뷰수 리턴하는 메소드
	 * @return 전체 상품 수
	 */
	public Integer selectCount(long productId) {
		Map<String, Long> params = new HashMap<>();
		params.put(PRODUCT_ID, productId);
		return getJdbc().queryForObject(SELECT_COUNT_BY_ID, params, Integer.class);
	}
}
