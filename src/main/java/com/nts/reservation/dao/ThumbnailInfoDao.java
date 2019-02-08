package com.nts.reservation.dao;

import static com.nts.reservation.dao.sql.ThumbnailInfoDaoSqls.SELECT_ALL_WITH_PAGING;
import static com.nts.reservation.dao.sql.ThumbnailInfoDaoSqls.SELECT_BY_CATERORY_WITH_PAGING;
import static com.nts.reservation.property.Const.CATEGORY_ID;
import static com.nts.reservation.property.Const.LIMIT;
import static com.nts.reservation.property.Const.START;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.Category;
import com.nts.reservation.dto.ThumbnailInfo;

/**
 * 썸네일 정보에 접근하는 클래스
 * @author si yoon
 *
 */
@Repository
public class ThumbnailInfoDao extends BaseDao {
	public ThumbnailInfoDao(DataSource dataSource) {
		super(dataSource);
	}

	private RowMapper<ThumbnailInfo> rowMapper = BeanPropertyRowMapper.newInstance(ThumbnailInfo.class);

	/**
	 * 전체 카테고리의 썸네일정보 셀렉트
	 * @param start 시작 인덱스
	 * @param limit 셀렉트할 갯수
	 * @return 썸네일 정보
	 */
	public List<ThumbnailInfo> selectFromAllCategorisWithPaging(Integer start, Integer limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put(START, start);
		params.put(LIMIT, limit);

		return getJDBC().query(SELECT_ALL_WITH_PAGING, params, rowMapper);
	}

	/**
	 * 특정 카테고리의 썸네일 정보 셀렉트
	 * @param category 셀렉트할 카테고리
	 * @param start 시작 인텍스
	 * @param limit 셀렉트할 갯수
	 * @return 해당 카테고리의 썸네일 정보
	 */
	public List<ThumbnailInfo> selectFromTheCategoryWithPaging(Category category, Integer start, Integer limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put(CATEGORY_ID, category.getId());
		params.put(START, start);
		params.put(LIMIT, limit);

		return getJDBC().query(SELECT_BY_CATERORY_WITH_PAGING, params, rowMapper);
	}
}
