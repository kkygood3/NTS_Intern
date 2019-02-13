package com.nts.reservation.dao;

import static com.nts.reservation.dao.sql.ReviewPageInfoDaoSqls.SELECT_BY_DISPLAY_INFO_ID;
import static com.nts.reservation.property.Const.DISPLAY_INFO_ID;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.ReviewPageInfo;

/**
 * 리뷰페이지 정보에 접근하는 클래스
 * @author si yoon
 *
 */
@Repository
public class ReviewPageInfoDao extends BaseDao {
	RowMapper<ReviewPageInfo> rowMapper = BeanPropertyRowMapper.newInstance(ReviewPageInfo.class);

	ReviewPageInfoDao(DataSource dataSource) {
		super(dataSource);
	}

	/**
	 * 상품에 따라 리뷰페이지 정보를 셀렉트한다
	 * @param displayInfoId 조회할 상품
	 * @return 쿼리실행결과 리뷰페이지 정보
	 */
	public ReviewPageInfo selectByDisplayInfoId(long displayInfoId) {
		Map<String, Object> params = new HashMap<>();
		params.put(DISPLAY_INFO_ID, displayInfoId);
		return getJdbc().queryForObject(SELECT_BY_DISPLAY_INFO_ID, params, rowMapper);
	}
}
